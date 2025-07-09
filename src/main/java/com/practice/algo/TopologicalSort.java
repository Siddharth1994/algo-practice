package com.practice.algo;

import javafx.util.Pair;

import java.util.*;

import static com.practice.algo.Util.adjListPopulationFromEdgesWIthInDegree;
import static com.practice.algo.Util.adjListPopulationFromGraphWithInDegree;

public class TopologicalSort {
    public Pair<Boolean,List<Integer>> sortFromGraph(int n, int[][] graph){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] inDegree = new int[n];
        adjListPopulationFromGraphWithInDegree(graph, inDegree, adjMap);
        return sort(inDegree, adjMap);
    }

    public Pair<Boolean,List<Integer>> sortFromEdges(int n, int[][] edges){
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] inDegree = new int[n];
        adjListPopulationFromEdgesWIthInDegree(edges, inDegree, adjMap);
        return sort(inDegree, adjMap);
    }

    private Pair<Boolean,List<Integer>> sort(int[] inDegree, Map<Integer, List<Integer>> adjMap){
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<inDegree.length;i++){
            if(inDegree[i]==0) q.add(i);
        }

        while(!q.isEmpty()){
            int u=q.poll();
            res.add(u);
            for(int v:adjMap.get(u)){
                if(--inDegree[v]==0) q.add(v);
            }
        }
        return new Pair<>(res.size()!=inDegree.length, res);
    }
}
