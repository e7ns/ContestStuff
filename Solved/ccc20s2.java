package Solved;

import java.io.*;
import java.util.*;

public class ccc20s2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] room = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[m+1][n+1];
        HashSet<Integer> visitedVals = new HashSet<>();
        q.add(new int[]{1, 1});
        visited[1][1] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == m && cur[1] == n) {
                System.out.println("yes");
                return;
            }
            double end = Math.sqrt(room[cur[0]][cur[1]]);
            for (int i = 1; i <= end; i++) {
                if (room[cur[0]][cur[1]] % i == 0) {
                    int a = room[cur[0]][cur[1]] / i;
                    if (a <= m && i <= n && !visited[a][i] && !visitedVals.contains(room[a][i])) {
                        q.add(new int[]{a, i});
                        visited[a][i] = true;
                        if (room[a][i] != room[m][n]) visitedVals.add(room[a][i]);
                    }
                    if (a <= n && i <= m && !visited[i][a] && !visitedVals.contains(room[i][a])) {
                        q.add(new int[]{i, a});
                        visited[i][a] = true;
                        if (room[i][a] != room[m][n]) visitedVals.add(room[i][a]);
                    }
                }
            }
        }
        System.out.println("no");
    }
}