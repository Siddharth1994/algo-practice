package com.practice.algo;

import javafx.util.Pair;

import java.util.*;

import static com.practice.algo.Util.adjMapPopulationFromEdges;
import static com.practice.algo.Util.adjMapPopulationFromGraph;

public class Prims {
    public Pair<Integer,int[]> mstFromGraph(int start, int n, int[][] graph){
        Map<Integer, Map<Integer,Integer>> adj = new HashMap<>();
        adjMapPopulationFromGraph(graph,adj);
        return prims(start, n, adj);
    }

    public Pair<Integer,int[]> mstFromEdges(int start, int n, int[][] edges){
        Map<Integer, Map<Integer,Integer>> adj = new HashMap<>();
        adjMapPopulationFromEdges(edges,adj);
        return prims(start, n, adj);
    }

    private Pair<Integer,int[]> prims(int start, int n, Map<Integer, Map<Integer,Integer>> adj){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[] mst = new int[n];
        Arrays.fill(mst,-1);
        if(!adj.containsKey(start)) throw new RuntimeException("Start node is not connected so MST can't be formed");
        int count=1,sum=0;
        queuePopulation(start, start, adj.get(start), mst, pq);

        while(!pq.isEmpty()){
            int[] temp=pq.poll();
            if(mst[temp[1]]==-1 && temp[1]!=start){
                queuePopulation(start, temp[1], adj.get(temp[1]), mst, pq);
                mst[temp[1]]=temp[0];
                sum+=temp[2];
                count++;
            }
        }

        if(count!=n) throw new RuntimeException("Graph is not connected so MST can't be formed");
        return new Pair<>(sum,mst);
    }

    private void queuePopulation(int start, int u, Map<Integer,Integer> adjMap, int[] mst, PriorityQueue<int[]> pq){
        for(int v:adjMap.keySet()){
            if(mst[v]==-1 && v!=start)
                pq.add(new int[]{u,v,adjMap.get(v)});
        }
    }


}
