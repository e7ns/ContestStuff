package Solved;

import java.util.*;
import java.io.*;

public class ccc18s3_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int startr = 0, startc = 0;
        char[][] grid = new char[R][C];
        int[][] visited = new int[R][C];
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = s.charAt(j);
                visited[i][j] = max;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'S') {
                    startr = i;
                    startc = j;
                    visited[i][j] = 0;
                } else if (grid[i][j] == 'C') {
                    visited[i][j] = -1;
                    for (int k = i+1; k < R; k++) {
                        if (grid[k][j] == '.' || grid[k][j] == 'S') {
                            visited[k][j] = -1;
                        } else if (grid[k][j] == 'W' || grid[k][j] == 'C') {
                            break;
                        }
                    }
                    for (int k = i-1; k >= 0; k--) {
                        if (grid[k][j] == '.' || grid[k][j] == 'S') {
                            visited[k][j] = -1;
                        } else if (grid[k][j] == 'W' || grid[k][j] == 'C') {
                            break;
                        }
                    }
                    for (int k = j+1; k < C; k++) {
                        if (grid[i][k] == '.' || grid[i][k] == 'S') {
                            visited[i][k] = -1;
                        } else if (grid[i][k] == 'W' || grid[i][k] == 'C') {
                            break;
                        }
                    }
                    for (int k = j-1; k >= 0; k--) {
                        if (grid[i][k] == 'S' || grid[i][k] == '.') {
                            visited[i][k] = -1;
                        } else if (grid[i][k] == 'W' || grid[i][k] == 'C') {
                            break;
                        }
                    }
                }
            }
        }

        Deque<Integer> dqr = new LinkedList<>();
        Deque<Integer> dqc = new LinkedList<>();
        dqr.addLast(startr);
        dqc.addLast(startc);
        while (!dqr.isEmpty()) {
            int r = dqr.pollFirst();
            int c = dqc.pollFirst();
            if (grid[r][c] == 'U') {
                if (grid[r-1][c] != 'W' && visited[r-1][c] != -1) {
                    if (visited[r-1][c] > visited[r][c]) {
                        visited[r-1][c] = visited[r][c];
                        dqr.add(r-1);
                        dqc.add(c);
                    }
                }
            } else if (grid[r][c] == 'D') {
                if (grid[r+1][c] != 'W' && visited[r+1][c] != -1) {
                    if (visited[r+1][c] > visited[r][c]) {
                        visited[r+1][c] = visited[r][c];
                        dqr.add(r+1);
                        dqc.add(c);
                    }
                }
            } else if (grid[r][c] == 'L') {
                if (grid[r][c-1] != 'W' && visited[r][c-1] != -1) {
                    if (visited[r][c-1] > visited[r][c]) {
                        visited[r][c-1] = visited[r][c];
                        dqr.add(r);
                        dqc.add(c-1);
                    }
                }
            } else if (grid[r][c] == 'R') {
                if (grid[r][c+1] != 'W' && visited[r][c+1] != -1) {
                    if (visited[r][c+1] > visited[r][c]) {
                        visited[r][c+1] = visited[r][c];
                        dqr.add(r);
                        dqc.add(c+1);
                    }
                }
            } else {
                if (grid[r-1][c] != 'W' && visited[r-1][c] != -1) {
                    if (visited[r-1][c] > visited[r][c]+1) {
                        visited[r-1][c] = visited[r][c]+1;
                        dqr.add(r-1);
                        dqc.add(c);
                    }
                } if (grid[r+1][c] != 'W' && visited[r+1][c] != -1) {
                    if (visited[r+1][c] > visited[r][c]+1) {
                        visited[r+1][c] = visited[r][c]+1;
                        dqr.add(r+1);
                        dqc.add(c);
                    }
                } if (grid[r][c-1] != 'W' && visited[r][c-1] != -1) {
                    if (visited[r][c-1] > visited[r][c]+1) {
                        visited[r][c-1] = visited[r][c]+1;
                        dqr.add(r);
                        dqc.add(c-1);
                    }
                } if (grid[r][c+1] != 'W' && visited[r][c+1] != -1) {
                    if (visited[r][c+1] > visited[r][c]+1) {
                        visited[r][c+1] = visited[r][c]+1;
                        dqr.add(r);
                        dqc.add(c+1);
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '.') {
                    if (0 < visited[i][j] && visited[i][j] < max) {
                        System.out.println(visited[i][j]);
                    } else {
                        System.out.println(-1);
                    }
                }
            }
        }
    }
}