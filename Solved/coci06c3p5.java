package Solved;

import java.io.*;
import java.util.*;
// got stuck at 100/140, broken at n=1
public class coci06c3p5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // nodes
        int m = Integer.parseInt(st.nextToken()); // edges
        if (n == 1) {
            System.out.println(0);
            return;
        }
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n+1);
        ArrayList<ArrayList<Integer>> rev = new ArrayList<>(n+1);
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from node
            int v = Integer.parseInt(st.nextToken()); // to node
            adj.get(u).add(v);
            rev.get(v).add(u);
        }
        int A = 1; // start
        int B = 2; // end
        // find all nodes that can be reached from A
        Queue<Integer> visit = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        visit.add(A);
        visited[A] = true;
        while(!visit.isEmpty()){
            int u = visit.poll();
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visit.add(v);
                    visited[v] = true;
                }
            }
        }
        // find all nodes that can reach B using reversed graph
        Queue<Integer> visit2 = new ArrayDeque<>();
        boolean[] visited2 = new boolean[n+1];
        visit2.add(B);
        visited2[B] = true;
        while(!visit2.isEmpty()){
            int u = visit2.poll();
            for (int v : rev.get(u)) {
                if (visited[v] && !visited2[v]) {
                    visit2.add(v);
                    visited2[v] = true;
                }
            }
        }
        int[] indegree = new int[n+1];
        int newN = 0;
        for (int u = 1; u <= n; u++) {
            if (!(visited[u] && visited2[u])) {
                adj.get(u).clear();
            } else {
                ArrayList<Integer> newEdges = new ArrayList<>();
                for (int v : adj.get(u)) {
                    if (visited[v] && visited2[v]) {
                        newEdges.add(v);
                        indegree[v]++;
                    }
                }
                adj.set(u, newEdges);
                newN++;
            }
        }
        Queue<Integer> topSort = new ArrayDeque<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        if (indegree[A] == 0) topSort.add(A);
        while (!topSort.isEmpty()) {
            int u = topSort.poll();
            sorted.add(u);
            for (int v : adj.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) topSort.add(v);
            }
        }
        if (sorted.size() < newN) {
            System.out.println("inf");
        } else {
            boolean overflow = false;
            long[] sums = new long[n+1];
            sums[A] = 1;
            for (int u : sorted) {
                for (int v : adj.get(u)) {
                    sums[v] += sums[u];
                    if (sums[v] >= 1000000000L) {
                        overflow = true;
                        sums[v] %= 1000000000L;
                    }
                }
            }
            if (overflow) {
                System.out.println(String.format("%09d", sums[B]));
            } else {
                System.out.println(sums[B]);
            }

        }
    }
}
