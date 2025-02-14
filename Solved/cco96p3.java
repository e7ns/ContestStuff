package Solved;

import java.io.*;

public class cco96p3 {
    static int[][] art;
    static int i;
    static int ans;

    static void fill(int[] pos, int r, String a) {
        if (a.charAt(i) == 'f') {
            for (int i = pos[0]; i < pos[0]+r; i++) {
                for (int j = pos[1]; j < pos[1]+r; j++) {
                    if (art[i][j] == 0) {
                        art[i][j] = 1;
                        ans++;
                    }
                }
            }
        } else if (a.charAt(i) == 'p') {
            r /= 2;
            int[][] dirs = {{0,0},{r,0},{r,r},{0,r}};
            for (int j = 0; j < 4; j++) {
                i++;
                fill(new int[]{pos[0]+dirs[j][0], pos[1]+dirs[j][1]}, r, a);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int z = 0; z < t; z++) {
            String a = br.readLine();
            String b = br.readLine();
            art = new int[32][32];
            ans = 0;
            i = 0;
            fill(new int[]{0,0}, 32, a);
            i = 0;
            fill(new int[]{0,0}, 32, b);
            System.out.println("There are "+ans+" black pixels.");
        }
    }
}