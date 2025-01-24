package Solved;

import java.io.*;
import java.util.*;

public class _2spooky4me {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> endpoints = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int candy = 0;
        int[][] decos = new int[N][3];
        HashSet<Integer> unique = new HashSet<>();
        ArrayList<Integer> spookiness = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            decos[i][0] = Integer.parseInt(st.nextToken());
            decos[i][1] = Integer.parseInt(st.nextToken());
            decos[i][2] = Integer.parseInt(st.nextToken());
            unique.add(decos[i][0]);
            unique.add(decos[i][1] + 1);
        }
        endpoints = new ArrayList<>(unique);
        endpoints.sort(null);
        for (int i : endpoints) {
            spookiness.add(0);
        }
        for (int i = 0; i < N; i++) {
            int a = endpoints.indexOf(decos[i][0]);
            int b = endpoints.indexOf(decos[i][1] + 1);
            for (int j = a; j < b; j++) {
                spookiness.set(j, spookiness.get(j)+decos[i][2]);
            }
        }
        candy += endpoints.getFirst()-1;
        candy += L - endpoints.getLast()+1;
        for (int i = 0; i < endpoints.size()-1; i++) {
            if (spookiness.get(i) < S){
                candy += endpoints.get(i+1)-endpoints.get(i);
            }
        }
        System.out.println(candy);
    }
}
