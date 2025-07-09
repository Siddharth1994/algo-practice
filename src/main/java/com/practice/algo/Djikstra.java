package com.practice.algo;

import java.util.*;
import java.util.function.Consumer;

import static com.practice.algo.Util.adjMapPopulationFromEdges;
import static com.practice.algo.Util.adjMapPopulationFromGraph;

public class Djikstra {

    public static void main(String[] args) {
        int[][] graph = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        int[][] edges = new int[][] {{5,7,85},{0,6,11},{4,7,79}};
        for(int a:new Djikstra().getShortestPathsFromGraph(0, graph.length, graph)) System.out.print(a + "\t");

        System.out.println();

        for(int a:new Djikstra().getShortestPathsFromEdges(0, 8, edges)) System.out.print(a + "\t");
    }

    public int[] getShortestPathsFromEdges(int src, int n, int[][] edges){
        return getShortestPathsFromEdgesAndPopulateAdjMatrix(src,n,edges, new HashMap<>());
    }

    public int[] getShortestPathsFromEdgesAndPopulateAdjMatrix(int src, int n, int[][] edges, Map<Integer, Map<Integer,Integer>> adj){
        adjMapPopulationFromEdges(edges,adj);
        return dijkstra(src, n, adj);
    }

    public int[] getShortestPathsFromGraph(int src, int n, int[][] graph){
        return getShortestPathsFromGraphAndPopulateAdjMatrix(src,n,graph, new HashMap<>());
    }

    public int[] getShortestPathsFromGraphAndPopulateAdjMatrix(int src, int n, int[][] graph, Map<Integer, Map<Integer,Integer>> adj){
        adjMapPopulationFromGraph(graph,adj);
        return dijkstra(src, n, adj);
    }


    private int[] dijkstra(int src, int n, Map<Integer, Map<Integer,Integer>> adj) {
        int[] dist = new int[n];
        Set<Integer> settled = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));

        Consumer<Integer> relaxation = (Integer u) -> {
            Map<Integer,Integer> curAdj = adj.get(u);
            for(int v:curAdj.keySet())
                if (!settled.contains(v)) {
                    int newEdgeVal = dist[u] + curAdj.get(v);
                    if(newEdgeVal<dist[v]){
                        dist[v] = newEdgeVal;
                        pq.add(new int[]{v, dist[v]});
                    }
                }
        };

        pq.add(new int[]{src, 0});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (settled.size()!=n && !pq.isEmpty()) {
            int u = pq.poll()[0];
            if (!settled.contains(u)){
                settled.add(u);
                relaxation.accept(u);
            }
        }
        return dist;
    }
}
