package Solved;

import java.io.*;
import java.util.*;

public class pickit {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            int[][] dp = new int[n][n];
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            for (int len = 2; len < n; len++) {
                for (int i = 0; i + len < n; i++) {
                    int j = i + len;
                    dp[i][j] = 0;
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + a[i] + a[k] + a[j]);
                    }
                }
            }
            System.out.println(dp[0][n-1]);
        }
    }
}
