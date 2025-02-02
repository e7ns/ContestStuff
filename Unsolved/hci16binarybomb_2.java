package Unsolved;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
// double bfs is wrong, use dijkstra's
public class hci16binarybomb_2 {
    static class State implements Comparable<State> {
        int x, y;
        BigInteger binaryValue;
        State(int x, int y, BigInteger binaryValue) {
            this.x = x;
            this.y = y;
            this.binaryValue = binaryValue;
        }

        @Override
        public int compareTo(State other) {
            return this.binaryValue.compareTo(other.binaryValue);
        }
    }
    public static void main(String[] args) throws IOException {
        // input and filling grid
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] S = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[] R = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[][] grid = new int[H][W];
        for (int i = 0; i < B; i++) {
            st = new StringTokenizer(br.readLine());
            int bt = Integer.parseInt(st.nextToken());
            int bx = Integer.parseInt(st.nextToken());
            int by = Integer.parseInt(st.nextToken());
            int rr = Integer.parseInt(st.nextToken());
            for (int y = by- rr; y <= by+ rr; y++) {
                if (y < 0) {continue;} else if (y >= H) {break;}
                int rx = rr - Math.abs(by-y);
                for (int x = bx-rx; x <= bx+rx; x++) {
                    if (x < 0) {continue;} else if (x >= W) {break;}
                    if (bt == 1) {
                        grid[y][x] = 1;
                    } else if (bt == 2) {
                        grid[y][x] = 0;
                    } else {
                        grid[y][x] = (grid[y][x] + 1) % 2;
                    }
                }
            }
        }

        // priority queue stores a[0] = x, a[1] = y, a[2] = value
        // still searching from R to S
        PriorityQueue<State> visit = new PriorityQueue<>();
        BigInteger startBinary = BigInteger.valueOf(grid[R[0]][R[1]]);
        visit.add(new State(R[0], R[1], startBinary));
        boolean[][] visited = new boolean[H][W];
        while (!visit.isEmpty()) {
            State current = visit.remove();
            visited[current.x][current.y] = true;
            if (current.x == S[0] && current.y == S[1]) {
                BigInteger temp = current.binaryValue.mod(BigInteger.valueOf(13371337));
                int result = temp.intValue();
                System.out.println(result);
                break;
            }
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];
                if (nx < 0 || nx >= H || ny < 0 || ny >= W || visited[nx][ny]) continue;
                BigInteger newBinary = current.binaryValue.shiftLeft(1).add(BigInteger.valueOf(grid[nx][ny]));
                visit.add(new State(nx, ny, newBinary));
            }
        }
    }
}
