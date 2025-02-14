package Unsolved;

import java.io.*;
import java.util.*;

public class _2048 {

    static int[][] move(int[][] in, int d) {
        if (d == 1) {
            for (int r = 0; r < 4; r++) {

            }
        } else if (d == 2) {
            for (int r = 0; r < 4; r++) {

            }
        } else if (d == 3) {
            for (int c = 0; c < 4; c++) {

            }
        } else if (d == 4) {
            for (int c = 0; c < 3; c++) {

            }
        }
        return in;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int z = 0; z < 5; z++) {
            StringTokenizer st;
            int[][] grid = new int[4][4];
            for (int r = 0; r < 4; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 4; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            for (int[] r : grid) {
                System.out.println(Arrays.toString(r));
            }
            System.out.println();
            Queue<int[][]> q = new LinkedList<>();
            q.add(grid);
            while (!q.isEmpty()) {
                int[][] curr = q.poll();
//                move(curr);
//                move(rotate(curr, 1));
//                move(rotate(curr, 2));
//                move(rotate(curr, 3));
            }





        }
    }
}
