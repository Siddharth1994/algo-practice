package com.practice.algo;

import javafx.util.Pair;

import java.util.PriorityQueue;
import java.util.function.BiFunction;

public class OptimalMergePattern {

    public int[] mergeArr(int[][] arr, BiFunction<int[],int[], int[]> merge){
        PriorityQueue<Pair<Integer,int[]>> pq = new PriorityQueue <>((a, b)->Integer.compare(b.getKey(),a.getKey()));

        for(int i=0;i<arr.length;i++)
            pq.add(new Pair<>(arr[i].length, arr[i]));

        while(pq.size()>1){
            int[] a=pq.poll().getValue();
            int[] b=pq.poll().getValue();
            int[] c=merge.apply(a,b);
            pq.add(new Pair<>(c.length,c));
        }
        return pq.peek().getValue();
    }
}
