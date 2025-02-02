package Solved;

import java.util.*;
import java.io.*;

public class sssp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // Ex. edge from node 3 to 5, weight 6 is stored in adj.get(3) as {3, 5, 6}
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from node
            int v = Integer.parseInt(st.nextToken()); // to node
            int w = Integer.parseInt(st.nextToken()); // weight
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }
        // dijkstra
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        PriorityQueue<int[]> visit = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]));
        visit.add(new int[]{1, 0});
        while(!visit.isEmpty()){
            int[] current = visit.poll();
            int u = current[0];
            if(visited[u]) continue;
            for(int[] edge : adj.get(u)){
                int v = edge[0];
                int w = edge[1];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + w;
                } else if (dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                }
                if (!visited[v]) {
                    visit.add(new int[]{v, dist[v]});
                }
            }
            visited[u] = true;
        }
        for(int i = 1; i <= n; i++) {
            System.out.println(dist[i]);
        }
    }
}
