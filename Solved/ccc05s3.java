package Solved;

import java.io.*;
import java.util.*;

public class ccc05s3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] A = {{1}};
        for (int z = 0; z < n; z++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] B = new int[r][c];
            int[][] C = new int[A.length*r][A[0].length*c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    B[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[0].length; j++) {
                    for (int k = 0; k < r; k++) {
                        for (int l = 0; l < c; l++) {
                            int rC = r*i+k;
                            int cC = c*j+l;
                            C[rC][cC] = A[i][j]*B[k][l];
                        }
                    }
                }
            }
            A = C;
        }
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE, minRow = Integer.MAX_VALUE;
        int[] cols = new int[A[0].length];
        int maxCol = Integer.MIN_VALUE, minCol = Integer.MAX_VALUE;
        for (int[] r : A){
            int rowsum = 0;
            for (int i = 0; i < r.length; i++){
                int c = r[i];
                rowsum += c;
                cols[i] += c;
                if (c > max) max = c;
                if (c < min) min = c;
            }
            if (rowsum > maxRow) maxRow = rowsum;
            if (rowsum < minRow) minRow = rowsum;
            System.out.println();
        }
        for (int i : cols) {
            if (i > maxCol) maxCol = i;
            if (i < minCol) minCol = i;
        }
        System.out.println(max + "\n" + min + "\n" + maxRow + "\n" + minRow + "\n" + maxCol + "\n" + minCol);
    }
}
