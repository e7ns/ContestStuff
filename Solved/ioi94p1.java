package Solved;

import java.io.*;
import java.util.*;

public class ioi94p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] triangle = new int[n+1][n+2];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i + 1; j++) {
                int cur = Integer.parseInt(st.nextToken());
                triangle[i][j] = Math.max(triangle[i-1][j], triangle[i-1][j-1]) + cur;
                if (triangle[i][j] > ans) {
                    ans = triangle[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}
