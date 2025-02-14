package Solved;

import java.io.*;
import java.util.*;

public class olyrim2 {

    static int query(int[] bit, int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += bit[pos];
            pos -= pos & -pos;
        }
        return sum;
    }

    static void update(int[] bit, int n, int pos, int d) {
        while (pos <= n) {
            bit[pos] += d;
            pos += pos & -pos;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[][] fenw = new int[32][n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            for (int b = 0; b < 32; b++) {
                if ((arr[i] & (1 << b)) != 0) { // if the b-th bit is set in arr[i]
                    update(fenw[b], n, i, 1);
                }
            }
        }

        for (int qi = 0; qi < q; qi++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (t == 1) { // update
                int newVal = r;
                for (int b = 0; b < 32; b++) {
                    boolean oldSet = (arr[l] & (1 << b)) != 0;
                    boolean newSet = (newVal & (1 << b)) != 0;
                    if (oldSet && !newSet) {
                        update(fenw[b], n, l, -1);
                    } else if (!oldSet && newSet) {
                        update(fenw[b], n, l, 1);
                    }
                }
                arr[l] = newVal;
            } else {
                int len = r - l + 1;
                long ans = 0;
                int[] cnt = new int[32];
                for (int b = 0; b < 32; b++) {
                    cnt[b] = query(fenw[b], r) - query(fenw[b], l - 1);
                }
                for (int b = 0; b < 32; b++) {
                    long bitVal = (1L << b);
                    int count = cnt[b];
                    if (t == 2) { // OR query
                        long totalPairs = ((long) len * (len - 1)) / 2;
                        long zeroCount = len - count;
                        long zeroPairs = (zeroCount * (zeroCount - 1)) / 2;
                        long onePairs = totalPairs - zeroPairs;
                        ans += bitVal * onePairs;
                    } else if (t == 3) { // AND query
                        long onePairs = ((long) count * (count - 1)) / 2;
                        ans += bitVal * onePairs;
                    } else if (t == 4) { // XOR query
                        long diffPairs = (long) count * (len - count);
                        ans += bitVal * diffPairs;
                    }
                }
                System.out.println(ans);
            }
        }
    }
}