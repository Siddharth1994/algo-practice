package com.practice.algo;

public class BinaryIndexTree {
    private final int[] binaryIndexTree;
    private final int[] arr;
    public static void main(String[] args){
        int[] num = {4,6,2,6,8};
        BinaryIndexTree bit=new BinaryIndexTree(num);
        System.out.println(bit.getSum(3)); // 18
        bit.updateBIT(2, 1);
        System.out.println(bit.getSum(3)); //17
    }

    public BinaryIndexTree(int[] arr){
        int n=arr.length;
        this.arr=arr;
        binaryIndexTree = new int[n+1];
        for(int i = 0; i < n; i++) addDeltaValBIT(i, arr[i]);
    }

    public void updateBIT(int index, int val){
        addDeltaValBIT(2, val-arr[index]);
    }

    public int getSum(int index) {
        int sum = 0;
        index = index + 1;
        while(index>0) {
            sum += binaryIndexTree[index];
            index -= index & (-index); // -index is 2’s complement + 1
        }
        return sum;
    }

    private void addDeltaValBIT(int index, int deltaVal) {
        index = index + 1;
        while(index < binaryIndexTree.length) {
            binaryIndexTree[index] += deltaVal;
            index += index & (-index);
        }
    }
}
