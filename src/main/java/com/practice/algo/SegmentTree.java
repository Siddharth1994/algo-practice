package com.practice.algo;

public class SegmentTree {
    public int getMin(int[] nums, int ql, int qh) {
        int n = nums.length;
        int[] treeArr = new int[(int)(Math.pow(2,Math.ceil(Math.log(n)/Math.log(2))+1) -1)];
        constructTree(0,n-1,0, treeArr, nums);
        return rangeMin(ql,qh,0,n-1,0, treeArr);
    }

    void constructTree(int left, int right, int pos, int[] treeArr, int[] arr){
        if(left==right) treeArr[pos]=arr[left];
        else{
            int mid=(left+right)/2;
            constructTree(left,mid,pos*2+1, treeArr, arr);
            constructTree(mid+1,right,pos*2+2, treeArr, arr);
            treeArr[pos]=Math.min(treeArr[2*pos+1], treeArr[2*pos+2]);
        }
    }

    int rangeMin(int ql,int qh,int l,int h, int pos, int[] treeArr){
        if(ql<=l && qh>=h) return treeArr[pos];
         else if(ql>h || qh<l) return Integer.MAX_VALUE;
         else {
            int mid = (l+h)/2;
            return Math.min(rangeMin(ql,qh,l,mid,pos*2+1, treeArr),
                    rangeMin(ql,qh,mid+1,h,pos*2+2, treeArr));
        }
    }

}
