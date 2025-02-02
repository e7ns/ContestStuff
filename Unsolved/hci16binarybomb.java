package Unsolved;

import java.io.*;
import java.util.*;

public class hci16binarybomb {
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
        // search for starting zeros (the ones closest to the endpoint)
        // since the binary number is the reversed path, we search from R to S
        Queue<int[]> visit =  new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        Queue<int[]> starting = new LinkedList<>();
        visit.add(new int[]{R[0], R[1]});
        visited[R[0]][R[1]] = true;
        boolean startingOne = false;
        if (grid[R[0]][R[1]] == 1) startingOne = true;
        int minDist = Math.abs(R[0] - S[0]) + Math.abs(R[1] - S[1]);
        while (!visit.isEmpty()) {
            if (startingOne) break;
            int[] pos = visit.remove();
            int dist = Math.abs(pos[0] - S[0]) + Math.abs(pos[1] - S[1]);
            if (dist <= minDist) {
                if (dist < minDist) {
                    starting.clear();
                    minDist = dist;
                }
                starting.add(pos);
            }
            int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] dir : dirs) {
                int[] i = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                if (i[0] >= 0 && i[0] < H && i[1] >= 0 && i[1] < W // within the grid
                        && !visited[i[0]][i[1]] && grid[i[0]][i[1]] == 0) { // new zero
                    visit.add(i);
                    visited[i[0]][i[1]] = true;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        // for each starting zero, find minimum value path
        for (int[] pos : starting) {
            int dist = Math.abs(pos[0] - S[0]) + Math.abs(pos[1] - S[1]);
            int[] num = new int[dist+1];
            Arrays.fill(num, 1);
            num[0] = grid[pos[0]][pos[1]];
            ArrayList<int[]> dirs = new ArrayList<>();
            if (pos[0] < S[0]) {
                dirs.add(new int[]{1, 0});
            } else if (pos[0] > S[0]) {
                dirs.add(new int[]{-1, 0});
            }
            if (pos[1] < S[1]) {
                dirs.add(new int[]{0, 1});
            } else if (pos[1] > S[1]) {
                dirs.add(new int[]{0, -1});
            }
            Queue<int[]> visit2 = new LinkedList<>();
            boolean dblOne = true;
            for (int[] dir : dirs) {
                int[] newPos = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                if (grid[newPos[0]][newPos[1]] == 0) {
                    visit2.add(new int[]{newPos[0], newPos[1], 1});
                    dblOne = false;
                }
            }
            if (dblOne) {
                for (int[] dir : dirs) {
                    int[] newPos = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                    visit2.add(new int[]{newPos[0], newPos[1], 1});
                }
            }
            while (!visit2.isEmpty()) {
                int[] cur = visit2.remove();
                num[cur[2]] = Math.min(num[cur[2]], grid[cur[0]][cur[1]]);
                dblOne = true;
                for (int[] dir : dirs) {
                    int[] newPos = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                    if (grid[newPos[0]][newPos[1]] == 0) {
                        visit2.add(new int[]{newPos[0], newPos[1], cur[2]+1});
                        dblOne = false;
                    }
                }
                if (dblOne) {
                    for (int[] dir : dirs) {
                        int[] newPos = new int[]{pos[0]+dir[0], pos[1]+dir[1]};
                        visit2.add(new int[]{newPos[0], newPos[1], cur[2]+1});
                    }
                }
            }
            int min = 0;
            for (int bit : num) {
                min = (min * 2 + bit) % 13371337;
            }
            ans = Math.min(ans, min);
        }
        System.out.println(ans);
    }
}
