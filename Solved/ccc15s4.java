package Solved;

import java.io.*;
import java.util.*;

public class ccc15s4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from node
            int v = Integer.parseInt(st.nextToken()); // to node
            int t = Integer.parseInt(st.nextToken()); // time
            int h = Integer.parseInt(st.nextToken()); // wear down
            adj.get(u).add(new int[]{v, t, h});
            adj.get(v).add(new int[]{u, t, h});
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        // dijkstra
        // dp[i][j] is the minimum time to get to island i with wear j
        int[][] dp = new int[n+1][k];
        for (int i = 0; i < n+1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[A][0] = 0;
        //in int[]{u, j, k}, u is the current island, j is the time to reach, k is the wear
        PriorityQueue<int[]> visit = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]));
        visit.add(new int[]{A, 0, 0});
        while(!visit.isEmpty()){
            int[] current = visit.poll();
            int u = current[0];
            int ot = current[1];
            int oh = current[2];
            if (ot > dp[u][oh]) continue;
            for(int[] edge : adj.get(u)){
                int v = edge[0];
                int t = edge[1];
                int h = edge[2];
                int nh = oh + h;
                if (nh < k) {
                    int nt = ot + t;
                    if (nt < dp[v][nh]) {
                        dp[v][nh] = nt;
                        visit.add(new int[]{v, nt, nh});
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, dp[B][i]);
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}