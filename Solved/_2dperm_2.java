package Solved;

import java.util.*;

public class _2dperm_2 {
    static int n, m;
    static ArrayList<HashSet<Integer>> counts;

    static class State {
        int[] nCount, mCount;

        State(int[] nCount, int[] mCount) {
            this.nCount = nCount.clone();
            this.mCount = mCount.clone();
        }

        // Encodes the state for HashSet storage
        public String encode() {
            return Arrays.toString(nCount) + "|" + Arrays.toString(mCount);
        }
    }

    public static void bfs() {
        Queue<State> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        // Initialize starting state
        int[] nCount = new int[n];
        int[] mCount = new int[m];
        queue.add(new State(nCount, mCount));
        visited.add(new State(nCount, mCount).encode());

        int t = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            counts.add(new HashSet<>());

            for (int s = 0; s < size; s++) {
                State current = queue.poll();

                for (int i = 0; i < n; i++) {
                    if (current.nCount[i] < m && (i == 0 || current.nCount[i] < current.nCount[i - 1])) {
                        int[] newNCount = current.nCount.clone();
                        int[] newMCount = current.mCount.clone();

                        newNCount[i]++;
                        newMCount[newNCount[i] - 1]++;

                        int pos = i * m + newNCount[i];
                        counts.get(t).add(pos);

                        State nextState = new State(newNCount, newMCount);
                        String encoded = nextState.encode();

                        if (!visited.contains(encoded)) {
                            visited.add(encoded);
                            queue.add(nextState);
                        }
                    }
                }
            }
            t++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int q = sc.nextInt();

        int[] qList = new int[q];
        for (int i = 0; i < q; i++) {
            qList[i] = sc.nextInt();
        }

        counts = new ArrayList<>();
        bfs();

        for (int i : qList) {
            System.out.println(counts.get(i - 1).size());
        }
    }
}
