package com.practice.algo;

import java.util.Arrays;

public class HamiltonianCycle {
    public void getHamiltonianCycles(int[][] graph) {
        int n=graph.length;
        int[] val=new int[n];
        boolean[] visited=new boolean[n];
        Arrays.fill(val,-1);
        val[0]=0;
        visited[0]=true;
        getHamiltonCycle(graph.length, 1, val, visited, graph);
    }


    private void getHamiltonCycle(int n, int i, int[] val, boolean[] visited, int[][] graph){
        for(int cur=0;cur<n;cur++){
            if(!isCurVertexEligible(n, i, val[i-1], cur, visited, graph)) continue;
            val[i]=cur;
            visited[cur]=true;
            if(i==n-1) store(val);
            else getHamiltonCycle(n,i+1,val,visited,graph);
            visited[cur]=false;
        }
    }

    private boolean isCurVertexEligible(int n, int i, int pre, int cur, boolean[] visited, int[][] graph){
        return !visited[cur] && graph[pre][cur]!=0 &&
                (i<n-1 || (i==n-1 && graph[cur][0]!=0));
    }

    private void store(int[] val){
        for(int node:val) System.out.print(node+"\t");
        System.out.println();
    }

}
