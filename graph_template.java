import java.io.*;
import java.util.*;

public class graph_template {
    static class State implements Comparable<State> {
        int node, weight, weight2;
        State(int node, int weight, int weight2) {
            this.node = node;
            this.weight = weight;
            this.weight2 = weight2;
        }
        public int compareTo(State o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // nodes
        int m = Integer.parseInt(st.nextToken()); // edges
        ArrayList<ArrayList<State>> adj = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from node
            int v = Integer.parseInt(st.nextToken()); // to node
            int w1 = Integer.parseInt(st.nextToken()); // weight 1
            int w2 = Integer.parseInt(st.nextToken()); // weight 2
            adj.get(u).add(new State(v, w1, w2));
            adj.get(v).add(new State(u, w1, w2));
        }
        int A = 0; // start
        int B = 0; // end
        PriorityQueue<State> visit = new PriorityQueue<>();
        visit.add(new State(A, 0, 0));
        while(!visit.isEmpty()){
            State current = visit.poll();
            int u = current.node;
            int w1 = current.weight;
            int w2 = current.weight2;
            if (u == B) break;
            for (State edge : adj.get(u)) {
                int v = edge.node;
                int nw1 = edge.weight;
                int nw2 = edge.weight2;
                // condition to add new states to the queue
            }
        }
    }
}
