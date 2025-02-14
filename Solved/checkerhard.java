package Solved;

import java.io.*;
import java.util.*;

public class checkerhard {
    static int[][] board;
    static int sum(int r1, int c1) {
        int sum = 0;
        for (int r = r1; r >= 1; r -= r&-r) {
            for (int c = c1; c >= 1; c -= c&-c) {
                sum += board[r][c];
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        board = new int[m+1][n+1];
        int[][] old = new int[m+1][n+1];
        while (true) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 0) break;
            if (t == 1) { // modify
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (r1 % 2 != c1 % 2) x = -x;

                for (int r = r1; r <= m; r += r&-r) {
                    for (int c = c1; c <= n; c += c&-c) {
                        board[r][c] -= old[r1][c1];
                        board[r][c] += x;
                    }
                }
                old[r1][c1] = x;
            } else if (t == 2) { // query
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int ans = sum(r2, c2) - sum(r2, c1-1) - sum(r1-1,c2) + sum(r1-1,c1-1);
                if (r1 % 2 != c1 % 2) ans = -ans;
                System.out.println(ans);
            }
        }
    }
}
