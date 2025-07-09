package com.practice.algo;

public class KMP {
    public boolean patternMatch(String text, String pattern){
        int[] lsp = new LongestSuffixPrefix().getLongestSuffixPrefixArr(pattern);
        int m=text.length(), n=pattern.length(), j=0;
        for(int i=0;i<m;i++){
            while(true){
                if(text.charAt(i)==pattern.charAt(j)){
                    j++;
                    break;
                }
                if(j==0) break;
                j=lsp[j-1];
            }
            if(j==n) return true;
        }
        return false;
    }
}
