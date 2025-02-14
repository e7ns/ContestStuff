package Solved;

import java.io.*;
import java.util.*;

public class ccc11s5 {
    static class State {
        BitSet row;
        int move;
        public State(BitSet row, int move) {
            this.row = row;
            this.move = move;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BitSet a = new BitSet(n);
        for (int i = 0; i < n; i++) {
            int d = Integer.parseInt(br.readLine());
            if (d == 1) a.set(i);
        }
        Queue<State> q = new ArrayDeque<>();
        HashSet<BitSet> visited = new HashSet<>();
        State initial = new State(a, 0);
        q.add(initial);
        while (!q.isEmpty()) {
            State s = q.poll();
            BitSet row = s.row;
            int l = 0;
            int r = 0;
            boolean set = false;
            while (r <= n) {
                if (r < n && row.get(r)) {
                    if (!set) l = r;
                    set = true;
                } else if (r > 0 && row.get(r-1)) {
                    if (r-l >= 4) row.clear(l,r);
                    set = false;
                }
                r++;
            }
            if (row.isEmpty()) {
                System.out.println(s.move);
                return;
            } else {
                for (int i = 0; i < n; i++) {
                    if (row.get(i)) continue;
                    if ((i > 0 && row.get(i-1)) || (i < n-1 && row.get(i+1))) {
                        BitSet newRow = (BitSet) row.clone();
                        newRow.set(i);
                        if (!visited.contains(newRow)) {
                            visited.add(newRow);
                            q.add(new State(newRow, s.move+1));
                        }
                    }
                }
            }
        }
    }
}
