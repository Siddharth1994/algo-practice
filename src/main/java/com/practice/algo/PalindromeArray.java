package com.practice.algo;

public class PalindromeArray {
    public boolean[][] getPalindromeArray(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        boolean[][] dp = new boolean[n][];
        for (int r = 0; r < n; r++) {
            dp[r] = new boolean[r + 1];
            for (int l = 0; l <= r; l++) {
                if (c[l] == c[r]) dp[r][l] = l + 1 > r - 1 || dp[r - 1][l + 1];
                else dp[r][l] = false;
            }
        }
        return dp;
    }
}
