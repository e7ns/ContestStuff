package Solved;

import java.io.*;
import java.util.*;

public class checkereasy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] board = new int[m+1][n+1];
        while (true) {
            String line = br.readLine();
            if (line.equals("0 0 0")) break;
            st = new StringTokenizer(line);
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (r % 2 == c % 2) {
                board[r][c] = x;
            } else {
                board[r][c] = -x;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = board[i][j] + board[i-1][j] + board[i][j-1] - board[i-1][j-1];
            }
        }
        while (true) {
            String line = br.readLine();
            if (line.equals("0 0 0 0")) break;
            st = new StringTokenizer(line);
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int ans = board[r2][c2] - board[r2][c1-1] - board[r1-1][c2] + board[r1-1][c1-1];
            if (r1 % 2 != c1 % 2) {
                ans = -ans;
            }
            System.out.println(ans);
        }


    }
}
