package com.practice.algo;

public class LongestSuffixPrefix {
    public int[] getLongestSuffixPrefixArr(String pat) {
        int n=pat.length(), j=0;
        int[] lsp=new int[n];
        for (int i=1;i<n;i++){
            while(true){
                if(pat.charAt(i)==pat.charAt(j)){
                    lsp[i]=++j;
                    break;
                }
                if(j==0){
                    lsp[i]=0;
                    break;
                }
                j=lsp[j-1];
            }
        }
        return lsp;
    }
}
