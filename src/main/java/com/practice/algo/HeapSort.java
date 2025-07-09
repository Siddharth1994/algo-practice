package com.practice.algo;

public class HeapSort {
    public void heapSort(int[] a){
        MaxHeapOps.heapBuild(a);
        for(int i=a.length-1;i>=0;i--){
            MaxHeapOps.delete(i, a);
        }
    }
}

class MaxHeapOps{
    private int[] a;
    public static void heapify(int i,int n,int[] a){
        int largest = i, l=2*i+1, r=2*i+2;
        if(l<n && a[l]>a[largest])
            largest=l;
        if(r<n && a[r]>a[largest])
            largest=r;
        if(largest!=i){
            Util.swap(largest,i,a);
            heapify(largest,n,a);
        }
    }

    public static void heapBuild(int[] a){
        int n=a.length, i= (int) ((double) (n / 2) -1);
        for(;i>=0;i--) heapify(i,n,a);
    }

    public static void delete(int i,int[] a){
        Util.swap(i,0,a);
        heapify(0,i,a);
    }
}
