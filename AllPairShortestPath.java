package com.practice.algo;

public class AllPairShortestPath {
    public int[][] getAllPairShortestPath(int[][] graph){
        int n= graph.length;
        int[][] cur=new int[n][n], prev = graph.clone();

        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(i==j) cur[i][j]=0;
                    else if(i==k || j==k ||
                            prev[i][k]==Integer.MAX_VALUE ||
                            prev[k][j]==Integer.MAX_VALUE) cur[i][j]=prev[i][j];
                    else cur[i][j]=Math.min(prev[i][k]+prev[k][j],prev[i][j]);
                }
            }
            int[][] temp=prev;
            prev=cur;
            cur=temp;
        }

        return cur;
    }
}
