package com.practice.algo;

public class MergeSort {
    public void sort(int[] a){
        mergeSort(0, a.length-1, a);
    }

    private void mergeSort(int l, int r,int[] a) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(l, mid, a);
            mergeSort(mid + 1, r, a);
            merge(l, mid, r, a);
        }
    }

    private void merge(int l, int m, int r, int[] a) {
        int i=0, j=0, k=l, n1=m-l+1, n2=r-m;
        int[] left=new int[n1], right=new int[n2];
        System.arraycopy(a, l, left, 0, n1);
        System.arraycopy(a, m+1, right, 0, n2);

        for(;i<n1 && j<n2;k++)
            a[k]= (left[i]<right[j]) ? left[i++] : right[j++];

        if(i!=n1) System.arraycopy(left, i, a, k, n1-i);
        else System.arraycopy(right, j, a, k, n2-j);
    }
}
