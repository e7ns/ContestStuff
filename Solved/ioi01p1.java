package Solved;

import java.io.*;
import java.util.*;

public class ioi01p1 {
    static int[][] BIT;
    static int m;
    static void update(int[] pos, int change) {
        for (int i = pos[0]; i <= m; i += i&-i) {
            for (int j = pos[1]; j <= m; j += j&-j) {
                BIT[i][j] += change;
            }
        }
    }
    static int query(int[] pos) {
        int out = 0;
        for (int i = pos[0]; i > 0; i -= i&-i) {
            for (int j = pos[1]; j > 0; j -= j&-j) {
                out += BIT[i][j];
            }
        }
        return out;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int g = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        BIT = new int[m+1][m+1];
        while (true) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if (t == 1) {
                int x = Integer.parseInt(st.nextToken())+1;
                int y = Integer.parseInt(st.nextToken())+1;
                int a = Integer.parseInt(st.nextToken());
                update(new int[] {x, y}, a);
            } else if (t == 2) {
                int x1 = Integer.parseInt(st.nextToken())+1;
                int y1 = Integer.parseInt(st.nextToken())+1;
                int x2 = Integer.parseInt(st.nextToken())+1;
                int y2 = Integer.parseInt(st.nextToken())+1;
                int ans = query(new int[] {x2, y2})
                        - query(new int[] {x2, y1-1})
                        - query(new int[] {x1-1, y2})
                        + query(new int[] {x1-1, y1-1});
                System.out.println(ans);
            } else if (t == 3) {
                break;
            }
        }
    }
}
