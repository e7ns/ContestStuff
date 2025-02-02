package Solved;

import java.io.*;
import java.util.*;

public class uacc1p4 {
    private static class node {
        HashMap<Integer, Double> out = new HashMap<>();
        ArrayList<Integer> in = new ArrayList<>();
        double vol = 0;
        double ans = 0;
        int pi = 0;
    }
    public static void main(String[] args) throws IOException {
        //input and initialization
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        node[] nodes = new node[n+1];
        int[] sorted = new int[n];
        for (int i = 0; i <= n; i++) {
            nodes[i] = new node();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (nodes[from].out.containsKey(to)) {
                nodes[from].out.put(to, 1.0);
            } else {
                nodes[from].out.put(to, weight/100.0);
                nodes[to].in.add(from);
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            nodes[i].pi = Integer.parseInt(st.nextToken());
        }
        // topological sort of nodes, put the ordered indexes into sorted
        int[] inDegree = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            inDegree[i] = nodes[i].in.size();
            if (inDegree[i] == 0) {
                q.add(i);
                nodes[i].vol = 1;
                nodes[i].ans = nodes[i].pi;
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            sorted[count] = u;
            count++;
            for (int v : nodes[u].out.keySet()) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }
        //calculating pollution values, stored in ans
        for (int k = 1; k < n; k++) {
            int i = sorted[k];
            ArrayList<Integer> a = nodes[i].in;
            double newp = 0;
            double newvol = 0;
            for (int j : a) {
                node in = nodes[j];
                newp += in.out.get(i)*in.vol*in.ans;
                newvol += in.out.get(i)*in.vol;
            }
            nodes[i].vol = newvol;
            nodes[i].ans = newp/newvol + nodes[i].pi;
        }
        //output
        for (int i = 1; i <= n; i++) {
            System.out.println(nodes[i].ans);
        }
    }
}
