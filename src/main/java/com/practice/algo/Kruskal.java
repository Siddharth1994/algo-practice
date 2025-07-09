package com.practice.algo;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Kruskal {
    public Pair<Integer,int[]> mstFromGraph(int n, int[][] graph){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for(int u=0;u<n;u++){
            for(int v=u+1;v<n;v++){
                if(graph[u][v]!=0)
                    pq.add(new int[]{u,v,graph[u][v]});
            }
        }
        return kruskal( n, pq);
    }

    public Pair<Integer,int[]> mstFromEdges(int n, int[][] edges){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        Collections.addAll(pq, edges);
        return kruskal(n, pq);
    }

    private Pair<Integer,int[]> kruskal(int n, PriorityQueue<int[]> pq) {
        int count=1,sum=0;
        int[] mst = new int[n];
        Arrays.fill(mst,-1);
        while(!pq.isEmpty()){
            int[] temp=pq.poll();
            boolean edgeAdded=false;
            if(mst[temp[0]]==-1 && mst[temp[1]]==-1){
                updateParent(temp[0], temp[1], mst);
                edgeAdded=true;
            } else if(mst[temp[0]]==-1 || mst[temp[1]]==-1){
                if(mst[temp[0]]==-1) updateParent(temp[1], temp[0], mst);
                else updateParent(temp[0], temp[1], mst);
                edgeAdded=true;
            }
            if(edgeAdded){
                count++;
                sum+=temp[2];
            }
        }

        if(count!=n) throw new RuntimeException("Graph is not connected so MST can't be formed");
        return new Pair<>(sum,mst);
    }

    private void updateParent(int parent, int child, int[] mst){
        mst[child]=parent;
        if(mst[parent]==-1) mst[parent]=-2;
    }
}
