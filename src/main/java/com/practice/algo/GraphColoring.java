package com.practice.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GraphColoring {

    public int[] colorGraph(int colorCount, int n, int[][] paths){
        int[] colArr = new int[n];
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int[] edge:paths)
            adjMap.computeIfAbsent(edge[0], (e)-> new ArrayList<>()).add(edge[1]);
        return colorGraph(colorCount,n,adjMap);
    }

    private int[] colorGraph(int m, int n, Map<Integer, List<Integer>> adjMap){
        int[] colArr = new int[n];
        for(int col=1;col<=m;col++){
            if(colorGraph(0, n, col, colArr, adjMap)) break;
        }
        return colArr;
    }

    private boolean colorGraph(int u, int n, int col, int[] colArr, Map<Integer, List<Integer>> adjMap){
        if(u==n) return true;
        boolean shouldColour=false;
        if(adjMap.containsKey(u)){
            for(int v:adjMap.get(u)){
                if(colArr[v]==0 || colArr[v]!=col) shouldColour=true;
                else{
                    shouldColour=false;
                    break;
                }
            }
        } else shouldColour=true;

        if(shouldColour){
            colArr[u]=col;
            for(int otherCol=1;otherCol<=4;otherCol++){
                if(colorGraph(u+1, n, otherCol, colArr, adjMap)) return true;
            }
        }
        return false;
    }

}
