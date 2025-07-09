package com.practice.algo;

import java.util.*;

public class ArticulationPoint {
    public static void main(String[] args){
        int[][] edges={{0,1}, {1,2}, {0,2}, {2,3}};
        System.out.println(new ArticulationPoint().findArticulationPoints(4,edges));
    }

    public List<Integer> findArticulationPoints(int n, int[][] edges){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Util.adjListPopulationFromEdges(edges, adjMap);
        List<Integer> res=new ArrayList<>();
        int[] discovery = new int[n], lowTime = new int[n], parent = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(parent, -1);
        dfs(0, 0, discovery, lowTime, parent, visited, adjMap, res);
        return res;
    }

    private void dfs(int u, int time, int[] discovery, int[] lowTime,
                     int[] parent, boolean[] visited,
                     Map<Integer, List<Integer>> adjMap, List<Integer> res){
        visited[u]=true;
        discovery[u]=time;
        lowTime[u]=time;
        int childCount=0;
        boolean isArticulationPoint=false;
        for(int v:adjMap.get(u)){
            if(v==parent[u]) continue;
            if(!visited[v]){
                parent[v]=u;
                childCount++;
                dfs(v, time+1, discovery, lowTime, parent, visited, adjMap, res);
                if(discovery[u]<=lowTime[v]) isArticulationPoint=true;
                else lowTime[u]=Math.min(lowTime[u], lowTime[v]);
            } else lowTime[u]=Math.min(lowTime[u], discovery[v]);

        }
        if((parent[u]==-1 && childCount>1) || (parent[u]!=-1 && isArticulationPoint)) res.add(u);
    }

}
