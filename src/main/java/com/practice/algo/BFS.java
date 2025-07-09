package com.practice.algo;

import java.util.*;

public class BFS {
    public Set<Integer> bfsFromGraph(int src, int[][] graph){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Util.adjListPopulationFromGraph(graph, adjMap);
        return bfs(src,adjMap);
    }

    public Set<Integer> bfsFromEdges(int src, int[][] edges){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Util.adjListPopulationFromEdges(edges, adjMap);
        return bfs(src,adjMap);
    }

    private Set<Integer> bfs(int src, Map<Integer, List<Integer>> adjMap){
        Set<Integer> res = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);res.add(src);

        while(!queue.isEmpty()){
            int u= queue.poll();
            for(int v:adjMap.get(u)){
                if(!res.contains(v)){
                    queue.add(v);
                    res.add(v);
                }
            }
        }
        return res;
    }
}
