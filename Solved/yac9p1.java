package Solved;

import java.io.*;
import java.util.*;

public class yac9p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int ans = 0;
        int[] s = new int[n];
        int[] t = new int[n];
        int[] tx = new int[n+1];
        StringTokenizer a = new StringTokenizer(br.readLine());
        StringTokenizer b = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(a.nextToken());
            t[i] = Integer.parseInt(b.nextToken());
            tx[t[i]] = i;
        }
        for (int i = 0; i < n-1; i++) {
            if (tx[s[i+1]] != tx[s[i]]+1) {
                ans++;
            }
        }
        System.out.println(ans+1);
    }
}
