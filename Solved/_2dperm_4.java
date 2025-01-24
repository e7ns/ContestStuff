package Solved;

import java.io.*;
import java.util.*;

public class _2dperm_4 {
    static int N;
    static int M;
    static int[][][] possible;
    static int[] ans;
    static void search(int[] rows, int[] cols, int val) {
        if (val == N*M){
            return;
        }
        for (int i = 0; i < N; i++) {
            if (rows[i] < M && (i == 0 || rows[i] < rows[i-1])) {
                rows[i]++;
                cols[rows[i]-1]++;
                if (possible[val][i][rows[i]-1] == 0){
                    possible[val][i][rows[i]-1] = 1;
                    ans[i*M+(rows[i]-1)]++;
                    search(rows, cols, val+1);
                }
                cols[rows[i]-1]--;
                rows[i]--;

            }
        }
        for (int i = 0; i < M; i++) {
            if (cols[i] < N && (i == 0 || cols[i] < cols[i-1])) {
                cols[i]++;
                rows[cols[i]-1]++;
                if (possible[val][cols[i]-1][i] == 0){
                    possible[val][cols[i]-1][i] = 1;
                    ans[(cols[i]-1)*M+i]++;
                    search(rows, cols, val+1);
                }
                rows[cols[i]-1]--;
                cols[i]--;
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] initRows = new int[N];
        int[] initCols = new int[M];
        possible = new int[N*M][N][M];
        ans = new int[N*M];
        search(initRows, initCols, 0);
        System.out.println(Arrays.toString(ans));

        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            System.out.println(ans[Integer.parseInt(st.nextToken())+1]);
        }
    }
}
