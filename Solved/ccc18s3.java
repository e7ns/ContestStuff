package Solved;

import java.io.*;
import java.util.*;

public class ccc18s3 {
    static char[][] grid;
    static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    static int[] conveyor(int[] pos, int[] start) {
        char c = grid[pos[0]][pos[1]];
        if (pos[0] == start[0] && pos[1] == start[1]) {
            return new int[]{0, 0};
        } else if (c == 'L' || c == 'R' || c == 'U' || c == 'D') {
            int[] np;
            if (c == 'L') {
                np = new int[]{pos[0], pos[1] - 1};
            } else if (c == 'R') {
                np = new int[]{pos[0], pos[1] + 1};
            } else if (c == 'U') {
                np = new int[]{pos[0] - 1, pos[1]};
            } else {
                np = new int[]{pos[0] + 1, pos[1]};
            }
            return conveyor(np, start);
        } else {
            return pos;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] legal = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(legal[i], true);
        }
        grid = new char[n][m];
        int[][] min = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }
        ArrayList<int[]> cameras = new ArrayList<>();
        ArrayList<int[]> empty = new ArrayList<>();
        int[] start = new int[2];
        for (int i = 0; i < n; i++) {
            String a = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = a.charAt(j);
                grid[i][j] = c;
                if (c == 'W') {
                    legal[i][j] = false;
                } else if (c == 'C') {
                    cameras.add(new int[]{i, j});
                } else if (c == 'L' || c == 'R' || c == 'U' || c == 'D') {
                    legal[i][j] = true;
                } else if (c == 'S') {
                    start = new int[]{i, j, 0};
                } else if (c == '.'){
                    empty.add(new int[]{i, j});
                }
            }
        }
        for (int[] pos : cameras) {
            legal[pos[0]][pos[1]] = false;
            for (int[] dir : dirs) {
                int[] np = {pos[0] + dir[0], pos[1] + dir[1]};
                char c = grid[np[0]][np[1]];
                while (c != 'W') {
                    c = grid[np[0]][np[1]];
                    if (!(c == 'L' || c == 'R' || c == 'U' || c == 'D')) {
                        legal[np[0]][np[1]] = false;
                    }
                    np[0] += dir[0];
                    np[1] += dir[1];
                }
            }
        }
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> dq = new ArrayDeque<>();
        if (legal[start[0]][start[1]]) {
            dq.add(start);
        }
        while (!dq.isEmpty()) {
            int[] pos = dq.pollFirst();
            char c = grid[pos[0]][pos[1]];
            min[pos[0]][pos[1]] = Math.min(min[pos[0]][pos[1]], pos[2]);
            visited[pos[0]][pos[1]] = true;
            int[] np;
            if (c == 'L' || c == 'R' || c == 'U' || c == 'D') {
                int[] temp = new int[]{pos[0], pos[1]};
                if (c == 'L') {
                    np = new int[]{pos[0], pos[1] - 1};
                } else if (c == 'R') {
                    np = new int[]{pos[0], pos[1] + 1};
                } else if (c == 'U') {
                    np = new int[]{pos[0] - 1, pos[1]};
                } else {
                    np = new int[]{pos[0] + 1, pos[1]};
                }
                np = conveyor(np, temp);

                if (legal[np[0]][np[1]] && !visited[np[0]][np[1]]) {
                    dq.addFirst(new int[]{np[0], np[1], pos[2]});
                }
            } else {
                for (int[] dir : dirs) {
                    np = new int[]{pos[0] + dir[0], pos[1] + dir[1]};
                    if (legal[np[0]][np[1]] && !visited[np[0]][np[1]]) {
                        dq.addLast(new int[]{np[0], np[1], pos[2] + 1});
                    }
                }
            }
        }
        for (int[] i : empty) {
            int ans = min[i[0]][i[1]];
            if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }
            System.out.println(ans);
        }
    }
}
