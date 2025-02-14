package Solved;

import java.io.*;
import java.util.*;

public class ccc16s4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            dp[i][i] = Integer.parseInt(st.nextToken());
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                for (int k = i; k < j; k++) {
                    if (dp[i][k] == dp[k+1][j] && dp[i][k] > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j]);
                    }
                }
                for (int k = i; k < j; k++) {
                    if (dp[i][k] > 0) {
                        for (int m = k+2; m <= j; m++) {
                            if (dp[i][k] == dp[m][j] && dp[i][k] > 0 && dp[k+1][m-1] > 0) {
                                dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][m - 1] + dp[m][j]);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int[] i : dp) {
            for (int j : i) {
                ans = Math.max(ans, j);
            }
        }
        System.out.println(ans);
    }
}
