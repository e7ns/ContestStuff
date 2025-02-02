package Solved;

import java.io.*;
import java.util.*;

public class ccc24s3_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer sta = new StringTokenizer(br.readLine());
        StringTokenizer stb = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        ArrayList<Integer> swipeStarts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(sta.nextToken());
        }
        boolean valid = false;
        int min = -1;

        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(stb.nextToken());
            if (i == 0 || B[i] != B[i - 1]) {
                valid = false;
                for (int j = min + 1; j < n; j++) {
                    if (B[i] == A[j]) {
                        swipeStarts.add(j);
                        valid = true;
                        min = j;
                        break;
                    }
                }
            }
            if (!valid) {
                break;
            }
        }

        if (valid) {
            ArrayList<int[]> left = new ArrayList<>();
            ArrayList<int[]> right = new ArrayList<>();
            int l = 0, r = -1;
            for (int i : swipeStarts) {
                r++;
                l = r;
                while (r < n && B[r] == A[i]) {
                    r++;
                }
                r--;
                if (l == r && r == i) continue;
                if (i < r && i > l) {
                    right.add(new int[]{i, r});
                    left.add(new int[]{l, i});
                }
                if (i <= l) {
                    right.add(new int[]{i, r});
                } else if (i >= r){
                    left.add(new int[]{l, i});
                }
            }
            right.sort((int[] a, int[] b) -> b[0]-a[0]);
            left.sort(Comparator.comparingInt((int[] a) -> a[1]));
            System.out.println("YES");
            System.out.println(left.size()+right.size());
            for (int[] move : left) {
                System.out.println("L "+move[0]+" "+move[1]);
            }
            for (int[] move : right) {
                System.out.println("R "+move[0]+" "+move[1]);
            }
        } else {
            System.out.println("NO");
        }

    }
}