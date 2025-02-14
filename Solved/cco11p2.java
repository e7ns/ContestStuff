package Solved;

import java.io.*;
import java.util.*;

public class cco11p2 {
    static class State implements Comparable<State> {
        int node, time, exposure;
        State(int node, int time, int exposure) {
            this.node = node;
            this.time = time;
            this.exposure = exposure;
        }
        public int compareTo(State o) {
            return Integer.compare(this.time, o.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<State>> adj = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from node
            int v = Integer.parseInt(st.nextToken()); // to node
            int d = Integer.parseInt(st.nextToken()); // distance
            int e = Integer.parseInt(st.nextToken()); // path type
            if (e == 1) { // sun
                e = d;
            }
            adj.get(u).add(new State(v, d, e));
            adj.get(v).add(new State(u, d, e));
        }
        int A = 0;
        int B = n-1;
        // dijkstra
        // map time to exposure
        TreeMap<Integer, Integer>[] best = new TreeMap[n];
        for (int i = 0; i < n; i++) {
            best[i] = new TreeMap<>();
        }
        best[A].put(0, 0);

        PriorityQueue<State> visit = new PriorityQueue<>();
        visit.add(new State(A, 0, 0));
        while(!visit.isEmpty()){
            State current = visit.poll();

            int u = current.node;
            int ot = current.time;
            int oe = current.exposure;
            Map.Entry<Integer, Integer> floor = best[u].floorEntry(oe);
            if (floor != null && floor.getValue() < ot) continue;

            for(State edge : adj.get(u)){
                int v = edge.node;
                int t = edge.time;
                int e = edge.exposure;
                int ne = oe + e;
                if (ne <= s) {
                    int nt = ot + t;
                    Map.Entry<Integer, Integer> floorV = best[v].floorEntry(ne);
                    if (floorV != null && floorV.getValue() <= nt) continue;

                    NavigableMap<Integer, Integer> tail = best[v].tailMap(ne, true);
                    ArrayList<Integer> toRemove = new ArrayList<>();
                    for (Map.Entry<Integer, Integer> entry : tail.entrySet()) {
                        if (entry.getValue() >= nt) {
                            toRemove.add(entry.getKey());
                        }
                    }
                    for (Integer key : toRemove) {
                        best[v].remove(key);
                    }

                    best[v].put(ne, nt);
                    visit.add(new State(v, nt, ne));
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : best[B].keySet()) {
            ans = Math.min(ans, best[B].get(i));
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
