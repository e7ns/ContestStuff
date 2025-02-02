package Unsolved;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class hci16binarybomb_3 {
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

    // Constants
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

        // Use Dijkstra-like approach from (Rx, Ry) to (Sx, Sy)
        // We'll store the minimal binary value found so far for each cell
        BigInteger[][] dist = new BigInteger[H][W];

        // The initial binary value for R is just grid[Rx][Ry]
        dist[Rx][Ry] = BigInteger.valueOf(grid[Rx][Ry]);

        // Priority Queue that orders by smallest BigInteger
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(Rx, Ry, dist[Rx][Ry]));

        // Directions: left, right, down, up
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!pq.isEmpty()) {
            State cur = pq.remove();
            int cx = cur.x;
            int cy = cur.y;
            BigInteger cval = cur.val;

            // If this is outdated (we found a smaller val for this cell), skip
            if (dist[cx][cy] == null || !dist[cx][cy].equals(cval)) {
                continue;
            }

            // If we've reached the shelter (Sx, Sy), we have the minimal path
            if (cx == Sx && cy == Sy) {
                // Output cval mod 13371337
                BigInteger ansMod = cval.mod(BigInteger.valueOf(MOD_BASE));
                System.out.println(ansMod.intValue());
                return;
            }

            // Explore neighbors
            for (int[] d : dirs) {
                int nx = cx + d[0];
                int ny = cy + d[1];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;

                // Build the new binary path value
                // shift left by 1, add the bit at (nx, ny)
                BigInteger newVal = cval.shiftLeft(1).add(BigInteger.valueOf(grid[nx][ny]));

                // If dist[nx][ny] not set or we found a smaller path
                if (dist[nx][ny] == null || newVal.compareTo(dist[nx][ny]) < 0) {
                    dist[nx][ny] = newVal;
                    pq.add(new State(nx, ny, newVal));
                }
            }
        }

        // If we exhaust the queue and never reach (Sx, Sy), no path was found
        System.out.println(-1);
    }
}
