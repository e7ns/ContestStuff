package Solved;

import java.io.*;
import java.util.*;
//first time using queue!!
//also max-heap priority queue
public class coci09c2p4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] treedist = new int[n][m];
        int[][] ans = new int[n][m];
        int[] V = new int[2];
        int[] J = new int[2];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> visit = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        for (int i = 0; i < n; i++) {
            String in = br.readLine();
            for (int j = 0; j < m; j++) {
                treedist[i][j] = -1;
                char pos = in.charAt(j);
                if (pos == '+') {
                    q.add(new int[]{i, j});
                    treedist[i][j] = 0;
                } else if (pos == 'V') {
                    V = new int[]{i, j};
                } else if (pos == 'J') {
                    J = new int[]{i, j};
                }
            }
        }
        while (!q.isEmpty()) {
            int[] pos = q.remove();
            int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] dir : dirs) {
                int[] i = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                if (i[0] >= 0 && i[0] < n && i[1] >= 0 && i[1] < m && treedist[i[0]][i[1]] == -1) {
                    treedist[i[0]][i[1]] = treedist[pos[0]][pos[1]] + 1;
                    q.add(i);
                }
            }
        }
        visit.add(new int[]{V[0], V[1], treedist[V[0]][V[1]]});
        visited[V[0]][V[1]] = true;
        ans[V[0]][V[1]] = treedist[V[0]][V[1]];
        while (!visit.isEmpty()) {
            int[] pos = visit.remove();
            if (pos[0] == J[0] && pos[1] == J[1]) {
                break;
            }
            int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] dir : dirs) {
                int[] i = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                if (i[0] >= 0 && i[0] < n && i[1] >= 0 && i[1] < m && !visited[i[0]][i[1]]) {
                    ans[i[0]][i[1]] = Math.min(treedist[i[0]][i[1]], ans[pos[0]][pos[1]]);
                    visit.add(new int[]{i[0], i[1], ans[i[0]][i[1]]});
                    visited[i[0]][i[1]] = true;
                }
            }
        }
        System.out.println(ans[J[0]][J[1]]);
    }
}
