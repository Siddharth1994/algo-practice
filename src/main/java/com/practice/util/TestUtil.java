package com.practice.util;

import com.practice.model.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TestUtil extends TreeNode {
    public static final int MOD = (int) (Math.pow(10, 9) + 7);

    public static TreeNode constructTree(Integer[] ar){
        return constructTree(0, ar);
    }

    private static TreeNode constructTree(int i, Integer[] ar){
        return (i>=ar.length || ar[i]==null) ? null : createNode(ar[i], constructTree(2*i+1, ar), constructTree(2*i+2, ar));
    }

    public static void print(int[][] arr){
        for(int[] ar:arr) print(ar);
        System.out.println();
    }

    public static void print(int[] ar){
        for(int a:ar) System.out.print(a+"\t");
        System.out.println();
    }

    public static int[] get1DIntArray(String str){
        return get1DIntList(str).stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<Integer> get1DIntList(String str){
        return Arrays.stream(str.replace("[","").replace("]","").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static String[] get1DStringArray(String str){
        return get1DStringList(str).toArray(new String[0]);
    }

    public static List<String> get1DStringList(String str){
        return Arrays.stream(str.replace("[","").replace("]","").split(","))
                .collect(Collectors.toList());
    }

    public static int[][] get2DIntArray(String str){
        String[] resStr= str.replace("[[","").replace("]]","").split("],\\[");
        int[][] res = new int[resStr.length][];
        AtomicInteger i = new AtomicInteger();
        Arrays.stream(resStr).map(TestUtil::get1DIntArray).forEach(ar->res[i.getAndIncrement()]=ar);
        return res;
    }

    public static String[][] get2DStringArray(String str){
        String[] resStr= str.replace("[[","").replace("]]","").split("],\\[");
        String[][] res = new String[resStr.length][];
        AtomicInteger i = new AtomicInteger();
        Arrays.stream(resStr).map(st->st.split(",")).forEach(st->res[i.getAndIncrement()]=st);
        return res;
    }

    public static List<List<Integer>> get2DIntList(String str){
        return Arrays.stream(get2DIntArray(str))
                .map(arr -> Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList())).collect(Collectors.toList());
    }

    public static List<List<String>> get2DStringList(String str){
        return Arrays.stream(get2DStringArray(str))
                .map(arr -> Arrays.stream(arr)
                .collect(Collectors.toList())).collect(Collectors.toList());
    }
}

