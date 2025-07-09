package com.practice.algo;

import javafx.util.Pair;

import java.util.PriorityQueue;

public class PartialItemKnapsack {
    public double maxProfit(int[] p, int[] w, int totalWeight){
        PriorityQueue<Pair<Double,Integer>> pq =new PriorityQueue<>((a, b)->Double.compare(b.getKey(),a.getKey()));
        double maxProfit = 0;
        for(int i=0;i<w.length;i++)
            pq.add(new Pair<>(p[i]*1.0/w[i], i));

        while(totalWeight!=0 && !pq.isEmpty()){
            int i=pq.poll().getValue();
            if(w[i]<=totalWeight){
                maxProfit += p[i];
                totalWeight -= w[i];
            } else{
                maxProfit += p[i]* totalWeight*1.0/w[i];
                totalWeight=0;
            }
        }
        return maxProfit;
    }
}
