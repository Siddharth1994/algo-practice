package com.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BellmanFord {
    public int[] getShortestPathsFromGraph(int start, int n, int[][] graph){
        List<int[]> edgesList=new ArrayList<>();
        for(int u=0;u<n;u++){
            for(int v=0;v<n;v++){
                if(graph[u][v]!=0)
                    edgesList.add(new int[]{u,v,graph[u][v]});
            }
        }
        return bellmanFord(start,n,edgesList);
    }

    public int[] getShortestPathsFromEdges(int start, int n, int[][] edges){
        List<int[]> edgesList=new ArrayList<>();
        Collections.addAll(edgesList, edges);
        return bellmanFord(start,n,edgesList);
    }

    private int[] bellmanFord(int start, int n, List<int[]> edgesList){
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start]=0;
        for(int i=0;i<edgesList.size();i++){
            for(int[] edge:edgesList){
                if(dp[edge[0]]!=Integer.MAX_VALUE)
                    dp[edge[1]]=Math.min(dp[edge[1]], dp[edge[0]]+edge[2]);
            }
        }
        return dp;
    }
}
