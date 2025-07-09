package com.practice.main;

import com.practice.util.TestUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestCodeRunner extends TestUtil {
    public static void main(String[] args) {
        String ars1="[2,3,4,5]", args2="[[1,4],[2,4],[3,6],[4,4]]";
        System.out.println(new TestCodeRunner().smallestRepunitDivByK(12727));
        //TestUtil.print(new TestCodeRunner().minInterval(TestUtil.get2DIntArray(args2), TestUtil.get1DIntArray(ars1)));
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        TreeMap<Integer,TreeSet<Integer>> map = new TreeMap<>();
        TreeSet<Integer> temp;
        int[] res=new int[queries.length];
        int leftVal, rightVal;
        for(int i=0;i<intervals.length;i++){
            map.computeIfAbsent(intervals[i][0], (e)->new TreeSet<>()).add(intervals[i][1]);
        }
        for(int i=0;i<queries.length;i++){
            leftVal=map.containsKey(queries[i])?queries[i]:map.lowerKey(queries[i]);
            temp=map.get(leftVal);
            rightVal=temp.contains(queries[i])?queries[i]:temp.higher(queries[i]);
            res[i]=rightVal-leftVal+1;
        }
        return res;
    }


    public int smallestRepunitDivByK(int k) {
        if(k%2==0 || k%5==0) return -1;
        else if(k==1 || k==11 || k==111 || k==1111 || k==11111) return String.valueOf(k).length();

        int[][] mul={{0,0},{10,1},{100,11},{1000,111},{10000,1111}, {100000,11111}};
        int length=String.valueOf(k).length(), val=mul[length-1][0]+mul[length-1][1];
        if(val<k) val=mul[length][0]+mul[length][1];
        int rem, res=String.valueOf(val).length();
        Set<Integer> rems=new HashSet<>();

        while(rems.add(rem = val%k)){
            if(rem==0) return res;
            int len=String.valueOf(rem).length();
            len=len==length?1:length-len;
            res+=len;
            val=rem*mul[len][0]+mul[len][1];
        }
        return -1;
    }

}


