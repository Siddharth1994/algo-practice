package com.practice.algo;

public class BitRotate {
    public static void main(String[] args){
        int n=5; // Number to rate
        int d=1; // rotate count
        int l=3; // number of bit to retain
        System.out.println(( (n << d) | (n >> (l - d)) )&l); //left shift
    }
}
