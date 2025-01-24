package Solved;

import java.io.*;
import java.util.*;

public class bgoi09p6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][][] values = new int[L+1][M+1][N+1];

        for (int n = 1; n <= N; n++){
            for (int m = 1; m <= M; m++) {
                for (int l = 1; l <= L; l++) {
                    int carat = Integer.parseInt(st.nextToken());
                    values[l][m][n] = carat
                    + values[l-1][m][n] + values[l][m-1][n] + values[l][m][n-1]
                    - values[l-1][m-1][n] - values[l-1][m][n-1] - values[l][m-1][n-1]
                    + values[l-1][m-1][n-1];
                }
            }
        }
        while (true){
            String a = br.readLine();
            if (a == null) break;
            st = new StringTokenizer(a);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());
            int sum = values[X][Y][Z]
                    - values[x][Y][Z] - values[X][y][Z] - values[X][Y][z]
                    + values[x][y][Z] + values[x][Y][z] + values[X][y][z]
                    - values[x][y][z];
            System.out.println(sum);
        }
    }
}
