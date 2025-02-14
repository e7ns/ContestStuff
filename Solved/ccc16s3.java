package Solved;

import java.io.*;
import java.util.*;

public class ccc16s3 {
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] search(int u, int len){
        int farthestNode = u;
        int maxLen = len;
        for (int v : tree[u]) {
            if (!visited[v]) {
                visited[v] = true;
                int[] candidate = search(v, len + 1);
                if (candidate[1] > maxLen) {
                    farthestNode = candidate[0];
                    maxLen = candidate[1];
                }
            }
        }
        return new int[]{farthestNode, maxLen};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] isPho = new boolean[n];
        st = new StringTokenizer(br.readLine());
        // set pho nodes
        for (int i = 0; i < m; i++) {
            int pho = Integer.parseInt(st.nextToken());
            isPho[pho] = true;
        }
        // initialize tree
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        // fill tree (adj list)
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        // prune useless nodes
        Queue<Integer> q = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            if (!isPho[u] && tree[u].size() == 1) {
                int v = tree[u].getFirst();
                tree[u].clear();
                tree[v].remove((Integer)u);
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            if (!isPho[u] && tree[u].size() == 1) {
                int v = tree[u].getFirst();
                tree[u].clear();
                tree[v].remove((Integer)u);
                q.add(v);
            }
        }
        // answer is 2 times (number of edges) - diameter (longest path)
        visited = new boolean[n];
        int p1 = -1;
        int nodes = 0;
        for (int i = 0; i < n; i++) {
            if (!tree[i].isEmpty()) {
                if (p1 == -1) {
                    visited[i] = true;
                    p1 = search(i, 0)[0];
                }
                nodes++;
            }
        }
        int loop = 2*nodes-2;
        visited = new boolean[n];
        visited[p1] = true;
        int d = search(p1,0)[1];
        System.out.println(loop-d);
    }
}
