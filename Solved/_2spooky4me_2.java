package Solved;

import java.io.*;
import java.util.*;

public class _2spooky4me_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> endpoints = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        TreeMap<Integer, Integer> dif = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            dif.put(a, dif.getOrDefault(a, 0) + s);
            dif.put(b + 1, dif.getOrDefault(b + 1, 0) - s);
        }
        int val = 0, key = 0, ans = 0;
        for (int k : dif.keySet()) {
            if (val < S){
                ans += k-key;
            }
            val += dif.get(k);
            key = k;
        }
        if (val < S){
            ans += L-dif.lastKey();
        }
        System.out.println(ans);
    }
}
