package USACOJan2025;

import java.io.*;
import java.util.*;

public class p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int z = 0; z < t; z++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ans = 0;
            ArrayList<int[]> ones = new ArrayList<>();
            ArrayList<int[]> twos = new ArrayList<>();
            boolean valid = true;
            int[][] pic = new int[n][n];

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    char colour = s.charAt(j);
                    if (colour == 'B') {
                        pic[i][j] = 2;
                        twos.add(new int[]{i, j});
                    } else if (colour == 'G') {
                        pic[i][j] = 1;
                        ones.add(new int[]{i, j});
                    }
                }
            }
            for (int j = twos.size() - 1; j >= 0; j--) {
                int[] i = twos.remove(j);
                ones.add(new int[]{i[0], i[1]});
                pic[i[0]][i[1]]--;
                if ((i[0]-b >= 0) && (i[1]-a >= 0) && (pic[i[0]-b][i[1]-a] > 0)){
                    if (pic[i[0]-b][i[1]-a] == 1){
                        int[] pos = {i[0]-b, i[1]-a};
                        for (int k = 0; k < ones.size(); k++) {
                            if (Arrays.equals(ones.get(k), pos)){
                                ones.remove(k);
                                break;
                            }
                        }

                    }
                    pic[i[0]-b][i[1]-a]--;
                    ans++;
                }
                else if ((i[0]-b >= 0) && (i[1]-a >= 0) && (pic[i[0]-b][i[1]-a] == 0)) {
                    ans = -1;
                    valid = false;
                    break;
                }
            }
            if (valid) {
                while (!ones.isEmpty()) {
                    int[] examine = ones.remove(ones.size()-1);
                    pic[examine[0]][examine[1]]--;

                    if ((examine[0]-b >= 0) && (examine[1]-a >= 0) && (pic[examine[0]-b][examine[1]-a] != 0)) {
                        pic[examine[0]-b][examine[1]-a]--;
                        int[] pos = {examine[0]-b, examine[1]-a};
                        for (int k = 0; k < ones.size(); k++) {
                            if (Arrays.equals(ones.get(k), pos)){
                                ones.remove(k);
                                break;
                            }
                        }

                    } else if ((examine[0]+b < n) && (examine[1]+a < n) && (pic[examine[0]+b][examine[1]+a] != 0)) {
                        pic[examine[0]+b][examine[1]+a]--;
                        int[] pos = {examine[0]+b, examine[1]+a};
                        for (int k = 0; k < ones.size(); k++) {
                            if (Arrays.equals(ones.get(k), pos)){
                                ones.remove(k);
                                break;
                            }
                        }
                    }
                    ans++;
                }
            }


            System.out.println(ans);
        }
    }
}
