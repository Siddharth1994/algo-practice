package com.practice.algo;

import java.util.HashMap;
import java.util.Map;

public class SubArrayWithDistinctElements {
    public int subArraysWithKDistinct(int[] nums, int k) {
        int res=0;
        Map<Integer, Integer> count=new HashMap<>();
        for (int l=0, m=0, r=0; r < nums.length; ++r) {
            if (count.getOrDefault(nums[r], 0) == 0){
                if (--k < 0) {
                    // Count of mth element will always be 1 as its start index of shorted sub-array
                    count.put(nums[m], 0);
                    l=++m;
                }
            }
            count.put(nums[r], count.getOrDefault(nums[r], 0)+1);
            if (k <= 0) {
                while (count.get(nums[m])!=1){ // Until mth element is found
                    count.put(nums[m], count.get(nums[m])-1);
                    m++;
                }
                k=0; // After above K is restored although this can be ignored
                res+= m-l+1;
            }
        }
        return res;
    }

    public int subArraysWithAtMostKDistinct(int[] nums, int k) {
        int l = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int r = 0; r < nums.length; ++r) {
            if (count.getOrDefault(nums[r], 0) == 0) k--;
            count.put(nums[r], count.getOrDefault(nums[r], 0) + 1);
            while (k < 0) {
                count.put(nums[l], count.get(nums[l]) - 1);
                if (count.get(nums[l]) == 0) k++;
                l++;
            }
            res += r - l + 1;
        }
        return res;
    }
}
