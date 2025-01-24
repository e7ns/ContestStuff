package Solved;

import java.util.*;

public class _2dperm_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int Q = sc.nextInt();
        int[] queries = new int[Q];
        for (int i = 0; i < Q; i++) {
            queries[i] = sc.nextInt();
        }

        int total = n*m;
        // Difference array for range updates
        int[] diff = new int[total+2];

        // Handle all non-corner cells
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                // Skip corners
                if (r == 1 && c == 1) continue;
                if (r == n && c == m) continue;

                int L = r + c - 1;
                int U = total - ((n - r) + (m - c));

                diff[L]++;
                if (U + 1 <= total) diff[U + 1]--;
            }
        }

        int[] counts = new int[total + 1];
        int cur = 0;
        for (int t = 1; t <= total; t++) {
            cur += diff[t];
            counts[t] = cur;
        }

        // Add corners
        counts[1] += 1;      // top-left corner
        counts[total] += 1;  // bottom-right corner

        for (int tq : queries) {
            System.out.println(counts[tq]);
        }
    }
}
