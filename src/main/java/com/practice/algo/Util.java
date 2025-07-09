package com.practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static void swap(int i, int j, int[] a){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    public static void adjMapPopulationFromGraph(int[][] graph,
                                                 Map<Integer, Map<Integer,Integer>> adjMap){
        for(int u=0;u<graph.length;u++){
            for(int v=0;v<graph[0].length;v++){
                if(graph[u][v]!=0){
                    adjMap.computeIfAbsent(u, e->new HashMap<>()).put(v,graph[u][v]);
                    adjMap.computeIfAbsent(v, e->new HashMap<>()).put(u,graph[u][v]);
                }
            }
        }
    }

    public static void adjListPopulationFromGraph(int[][] graph,
                                                 Map<Integer, List<Integer>> adjMap){
        for(int u=0;u<graph.length;u++){
            for(int v=0;v<graph[0].length;v++){
                if(graph[u][v]!=0){
                    adjMap.computeIfAbsent(u, e->new ArrayList<>()).add(v);
                    adjMap.computeIfAbsent(v, e->new ArrayList<>()).add(u);
                }
            }
        }
    }

    public static void adjMapPopulationFromEdges(int[][] edges,
                                                 Map<Integer, Map<Integer,Integer>> adjMap){
        for(int[] edge:edges){
            adjMap.computeIfAbsent(edge[0], (e)-> new HashMap<>()).put(edge[1], edge[2]);
            adjMap.computeIfAbsent(edge[1], (e)-> new HashMap<>()).put(edge[0], edge[2]);
        }
    }

    public static void adjListPopulationFromGraphWithInDegree(int[][] graph, int[] inDegree,
                                                              Map<Integer, List<Integer>> adjMap){
        for(int u=0;u<graph.length;u++){
            for(int v=0;v<graph[0].length;v++){
                if(graph[u][v]!=0){
                    adjMap.computeIfAbsent(u, e->new ArrayList<>()).add(v);
                    inDegree[v]++;
                }
            }
        }
    }

    public static void adjListPopulationFromEdgesWIthInDegree(int[][] edges, int[] inDegree,
                                                              Map<Integer, List<Integer>> adjMap){
        for(int[] edge:edges){
            adjMap.computeIfAbsent(edge[0], e->new ArrayList<>()).add(edge[1]);
            inDegree[edge[1]]++;
        }
    }

    public static void adjListPopulationFromEdges(int[][] edges,
                                                 Map<Integer, List<Integer>> adjMap){
        for(int[] edge:edges){
            adjMap.computeIfAbsent(edge[0], (e)-> new ArrayList<>()).add(edge[1]);
            adjMap.computeIfAbsent(edge[1], (e)-> new ArrayList<>()).add(edge[0]);
        }
    }

    public static void outMapPopulationFromGraph(int[][] graph,
                                                 Map<Integer, Map<Integer,Integer>> outMap){
        for(int u=0;u<graph.length;u++){
            for(int v=0;v<graph[0].length;v++){
                if(graph[u][v]!=0)
                    outMap.computeIfAbsent(v, e->new HashMap<>()).put(u,graph[u][v]);
            }
        }
    }

    public static void outMapPopulationFromEdges(int[][] edges,
                                                 Map<Integer, Map<Integer,Integer>> outMap){
        for(int[] edge:edges)
            outMap.computeIfAbsent(edge[1], (e)-> new HashMap<>()).put(edge[0], edge[2]);
    }

    public static class Node{
        int val;
        Node left, right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static Node buildTree(List<Integer> arr){
        return build(0,arr);
    }

    private static Node build(int i, List<Integer> arr){
        return (i>=arr.size() || arr.get(i)==null) ? null :
                new Node(arr.get(i), build(2*i+1, arr), build(2*i+2, arr));
    }
}

