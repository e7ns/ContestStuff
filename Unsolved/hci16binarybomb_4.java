package Unsolved;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class hci16binarybomb_4 {
    static class State implements Comparable<State> {
        int x, y;
        BigInteger val;
        State(int x, int y, BigInteger val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        // Compare by val (the current binary path value), smallest first
        @Override
        public int compareTo(State other) {
            return this.val.compareTo(other.val);
        }
    }

    static final int MOD_BASE = 13371337;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int Sx = Integer.parseInt(st.nextToken());
        int Sy = Integer.parseInt(st.nextToken());
        int Rx = Integer.parseInt(st.nextToken());
        int Ry = Integer.parseInt(st.nextToken());

        int[][] grid = new int[H][W];

        // fill grid
        for (int i = 0; i < B; i++) {
            st = new StringTokenizer(br.readLine());
            int bombType = Integer.parseInt(st.nextToken());
            int bx = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());
            int rr = Integer.parseInt(st.nextToken());

            int yMin = Math.max(0, by - rr);
            int yMax = Math.min(H - 1, by + rr);
            for (int y = yMin; y <= yMax; y++) {
                // horizontal radius for this row
                int rX = rr - Math.abs(by - y);
                int xMin = Math.max(0, bx - rX);
                int xMax = Math.min(W - 1, bx + rX);
                if (bombType == 1) {
                    for (int x = xMin; x <= xMax; x++) {
                        grid[y][x] = 1;
                    }
                } else if (bombType == 2) {
                    for (int x = xMin; x <= xMax; x++) {
                        grid[y][x] = 0;
                    }
                } else {
                    for (int x = xMin; x <= xMax; x++) {
                        grid[y][x] ^= 1;
                    }
                }
            }
        }
        for (int i = 0; i < H; i++) {
            System.out.println (Arrays.toString(grid[i]));
        }
        //begin dijkstra from R to S
        Queue<int[]> visit =  new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        Queue<int[]> starting = new LinkedList<>();
        visit.add(new int[]{Rx, Ry});
        boolean startingOne = false;
        if (grid[Ry][Rx] == 1) startingOne = true;
        int minDist = Math.abs(Rx - Sx) + Math.abs(Ry - Sy);
        while (!visit.isEmpty()) {
            if (startingOne) break;
            int[] pos = visit.remove();
            visited[pos[1]][pos[0]] = true;
            int dist = Math.abs(pos[0] - Sx) + Math.abs(pos[1] - Sy);
            if (dist <= minDist) {
                if (dist < minDist) {
                    starting.clear();
                    minDist = dist;
                }
                starting.add(pos);
            }
            int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] dir : dirs) {
                int nx = pos[0]+dir[0];
                int ny = pos[1]+dir[1];
                if (nx < 0 || nx > W-1 || ny < 0 || ny > H-1 || visited[ny][nx] || grid[ny][nx] == 1) continue;
                visit.add(new int[]{nx, ny});
            }
        }
        for (int[] i : starting) {
            System.out.println(i[0] + " " + i[1]);
        }
    }
}