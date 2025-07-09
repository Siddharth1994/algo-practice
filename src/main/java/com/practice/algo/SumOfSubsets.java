package com.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfSubsets {
    private List<String> res;
    public List<String> sum(int weight, int[] weights){
        res=new ArrayList<>();
        sum(0,Arrays.stream(weights).sum(), weight,"", weights);
        return res;
    }

    private void sum(int i, int sumWeight, int rem, String selected, int[] weights){
        if(i==weights.length) return;
        sumWeight-=weights[i];
        if(rem<=sumWeight) sum(i+1,sumWeight,rem,selected+"0",weights);
        rem-=weights[i];
        selected+="1";
        if(rem==0){
            for(int j=i+1;j<weights.length;j++) selected+="0";
            res.add(selected);
        }
        else if(rem<=sumWeight) sum(i+1,sumWeight,rem,selected,weights);;
    }
}
