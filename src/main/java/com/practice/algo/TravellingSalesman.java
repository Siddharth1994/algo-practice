package com.practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//User function Template for Java

class TravellingSalesman {
    public static void main(String[] args){
        int[][] dist = {{0,111}, {112,0}};
        System.out.println(new TravellingSalesman().total_cost(dist));
    }
    public int total_cost(int[][] dist) {
        int n = dist.length, m = (int)Math.pow(2, n-1) - 1;
        if(n == 1) return dist[0][0];
        int[][] dp = new int[n][m+1];
        Map<Integer, List<Integer>> bitPos = new HashMap<>();
        for(int i=0;i<m;i++) bitPos.computeIfAbsent(Integer.bitCount(i), key -> new ArrayList<>()).add(i);
        for(int i=1;i<n;i++) dp[i][0] = dist[i][0];
        for(int subsetCount = 1; subsetCount < n-1; subsetCount++) {
            List<Integer> bitPosVals =  bitPos.get(subsetCount);
            for(int rowVal = 1; rowVal < n; rowVal++) {
                int rowPackedVal = 1<<(rowVal-1);
                for (int colPackedVals : bitPosVals) {
                    if ((rowPackedVal & colPackedVals) != rowPackedVal)
                        populateMin(subsetCount, rowVal, colPackedVals, n, dist, dp);
                }
            }
        }
        populateMin(n-1, 0, m, n, dist, dp);
        return dp[0][m];
    }

    private void populateMin(int subsetCount, int rowVal, int colPackedVals, int n, int[][] dist, int[][] dp) {
        int l = 0;
        dp[rowVal][colPackedVals] = Integer.MAX_VALUE;
        for(int colVal=1;colVal<=n && l<subsetCount;colVal++){
            int colPackedVal = 1 << (colVal-1);
            if((colPackedVal & colPackedVals) == colPackedVal) {
                dp[rowVal][colPackedVals] = Math.min(dp[rowVal][colPackedVals], dist[rowVal][colVal] + dp[colVal][colPackedVals ^ colPackedVal]);
                l++;
            }
        }
    }
}