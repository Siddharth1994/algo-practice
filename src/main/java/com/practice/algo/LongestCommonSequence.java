package com.practice.algo;

public class LongestCommonSequence {
    public String getLCS(String a, String b){
        int m=a.length(),n=b.length();
        StringBuilder result= new StringBuilder();
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(a.charAt(i-1)!=b.charAt(j-1)) dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                else dp[i][j]=1+dp[i-1][j-1];
            }
        }

        for(int j=n, i=m;j>=1 && i>=1;j--){
            if(dp[i][j]!=dp[i-1][j] && dp[i][j]!=dp[i][j-1])
                result.insert(0, a.charAt(--i));
        }
        return result.toString();
    }
}
