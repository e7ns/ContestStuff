package Unsolved;

import java.io.*;
import java.util.*;

public class yac9p4 {
    static int choose(int a, int b){
        double out = 1;
        for (int i = 1; i <= b; i++){
            out *= (a+1-i);
            out /= i;
        }
        return (int) Math.round(out);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st1.nextToken());
            B[i] = Integer.parseInt(st2.nextToken());
        }
        System.out.println(choose(2*n, n));
    }
}
