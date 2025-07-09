package com.practice.algo;

import javafx.util.Pair;

import java.util.Arrays;

public class DisjointSet {
    private boolean isCycle;
    public Pair<Boolean,int[]> getParentArrAndIsCycleFromGraph(int[][] graph){
        int n=graph.length;
        int[] parent = getParentArr(n);
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(graph[i][j]==1){
                    updateParent(i,j, parent);
                }
            }
        }
        return new Pair<>(isCycle,parent);
    }

    public Pair<Boolean,int[]> getParentArrAndIsCycleFromEdges(int n, int[][] edges){
        int[] parent =  getParentArr(n);
        for(int[] edge:edges){
            updateParent(edge[0],edge[1], parent);
        }
        return new Pair<>(isCycle,parent);
    }

    private int[] getParentArr(int n){
        int[] parent = new int[n];
        Arrays.fill(parent,-1);
        return parent;
    }

    private void updateParent(int left, int right, int[] parent){
        int leftParent= findParent(left, parent);
        int rightParent= findParent(right, parent);
        if(leftParent==rightParent) isCycle=true;
        int newParentVal = parent[leftParent] + parent[rightParent];
        if(parent[leftParent]<=parent[rightParent]){
            unionWithCollapse(right,leftParent,parent);
            parent[leftParent]=newParentVal;
        } else{
            unionWithCollapse(left,rightParent,parent);
            parent[rightParent]=newParentVal;
        }
    }

    private int findParent(int index, int[] parent){
        return parent[index]<0 ? index : findParent(parent[index], parent);
    }

    private void unionWithCollapse(int index, int parentIndex, int[] parent){
        if(index>=0){
            unionWithCollapse(parent[index], parentIndex, parent);
            parent[index]=parentIndex;
        }
    }
}
