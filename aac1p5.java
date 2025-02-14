import java.io.*;
import java.util.*;

public class aac1p5 {
    static class State implements Comparable<State> {
        int node, weight;
        State(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        public int compareTo(State o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // nodes
        StringTokenizer st;

        ArrayList<ArrayList<State>> adj = new ArrayList<>(n+1);
        for(int i = 0; i < n+1; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // from node
            int v = Integer.parseInt(st.nextToken()); // to node
            int w1 = Integer.parseInt(st.nextToken()) % 2; // weight 1

            adj.get(u).add(new State(v, w1));
            adj.get(v).add(new State(u, w1));
        }
        int A = 0; // start
        int B = 0; // end
        PriorityQueue<State> visit = new PriorityQueue<>();
        visit.add(new State(A, 0));
        while(!visit.isEmpty()){
            State current = visit.poll();
            int u = current.node;
            int w1 = current.weight;
            if (u == B) break;
            for (State edge : adj.get(u)) {
                int v = edge.node;
                int nw1 = edge.weight;
                // condition to add new states to the queue
            }
        }
    }
}
