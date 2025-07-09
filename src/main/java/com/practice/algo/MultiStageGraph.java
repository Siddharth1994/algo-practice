package com.practice.algo;

import javafx.util.Pair;

import java.util.*;

public class MultiStageGraph {
    public Pair<int[], int[]> multiStageFromGraph(int start, int end, int n, int[][] graph){
        Map<Integer,Map<Integer,Integer>> outMap = new HashMap<>();
        Util.outMapPopulationFromGraph(graph, outMap);
        return multiStage(start,end,n,outMap);
    }

    public Pair<int[], int[]> multiStageFromEdges(int start, int end, int n, int[][] graph){
        Map<Integer,Map<Integer,Integer>> outMap = new HashMap<>();
        Util.outMapPopulationFromEdges(graph, outMap);
        return multiStage(start,end,n,outMap);
    }

    private Pair<int[], int[]> multiStage(int start, int end, int n, Map<Integer,Map<Integer,Integer>> outMap){
        int[] cost = new int[n];
        int[] parent = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        cost[end]=0;parent[end]=end;

        Queue<Integer> q = new LinkedList<>();
        q.add(end);
        while(!q.isEmpty() && q.peek()!=start){
            int v=q.poll();
            Map<Integer,Integer> out=outMap.get(v);
            for(int u:out.keySet()){
                int val=cost[v]+out.get(u);
                if(parent[u]==-1) q.add(u);
                if(val<cost[u]){
                    cost[u]=val;
                    parent[u]=v;
                }
            }
        }
        return new Pair<>(cost, parent);
    }
}
