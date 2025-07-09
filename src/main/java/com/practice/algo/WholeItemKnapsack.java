package com.practice.algo;

import javafx.util.Pair;

import java.util.Arrays;

public class WholeItemKnapsack {
    public Pair<Integer,boolean[]> maxProfit(int[] profits, int[] weights, int totalWeight){
        int n=weights.length, m=totalWeight+1, tempWeight=totalWeight;
        int[][] dp=new int[n][m];
        boolean[] selected=new boolean[n];
        Arrays.fill(dp[0],weights[0],m,profits[0]);

        for(int i=1;i<n;i++){
            for(int w=1;w<m;w++){
                if(w<weights[i]) dp[i][w]=dp[i-1][w];
                else dp[i][w]=Math.max(dp[i-1][w], dp[i-1][w-weights[i]]+profits[i]);
            }
        }

        for(int i=n-1;i>0;i--){
            if(dp[i][tempWeight]!=dp[i-1][tempWeight]){
                selected[i]=true;
                tempWeight-=weights[i];
            }
        }
        if(tempWeight==weights[0]) selected[0]=true;
        return new Pair<>(dp[n-1][totalWeight], selected);
    }
}
