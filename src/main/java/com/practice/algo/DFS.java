package com.practice.algo;

import java.util.*;

public class DFS {
    public Set<Integer> dfsFromGraph(int src, int[][] graph){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Util.adjListPopulationFromGraph(graph, adjMap);
        return dfs(src,adjMap);
    }

    public Set<Integer> dfsFromEdges(int src, int[][] edges){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Util.adjListPopulationFromEdges(edges, adjMap);
        return dfs(src,adjMap);
    }

    private Set<Integer> dfs(int src, Map<Integer, List<Integer>> adjMap){
        Set<Integer> res = new LinkedHashSet<>();
        traverse(src, res, adjMap);
        return res;
    }

    private void traverse(int u, Set<Integer> res, Map<Integer, List<Integer>> adjMap){
        res.add(u);
        for(int v:adjMap.get(u)){
            if(!res.contains(v))
                traverse(v,res,adjMap);
        }
    }
}
