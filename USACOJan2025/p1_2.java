package USACOJan2025;

import java.io.*;
import java.util.*;

public class p1_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int z = 0; z < t; z++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ans = 0;
            ArrayList<Integer> ones = new ArrayList<>();
            ArrayList<Integer> twos = new ArrayList<>();
            boolean valid = true;
            int[] pic = new int[n*n];

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    char colour = s.charAt(j);
                    if (colour == 'B') {
                        pic[i*n+j] = 2;
                        twos.add(i*n+j);
                    } else if (colour == 'G') {
                        pic[i*n+j] = 1;
                        ones.add(i*n+j);
                    }
                }
            }
            for (int i = twos.size() - 1; i >= 0; i--) {
                int pos = twos.remove(i);
                ones.add(pos);
                pic[pos]--;
                int newPos = pos-b*n-a;
                if (newPos >= 0 && pic[newPos] > 0){
                    if (pic[newPos] == 1){
                        ones.remove((Integer) newPos);
                    }
                    pic[newPos]--;
                    ans++;
                }
                else {
                    ans = -1;
                    valid = false;
                    break;
                }
            }
            if (valid) {
                while (!ones.isEmpty()) {
                    int pos = ones.remove(ones.size()-1);
                    pic[pos]--;
                    int newPos = pos-b*n-a;
                    if (newPos >= 0 && pic[newPos] > 0) {
                        pic[newPos]--;
                        ones.remove((Integer) newPos);
                    } else {
                        newPos = pos+b*n+a;
                        if (newPos < n*n && pic[newPos] > 0) {
                            pic[newPos]--;
                            ones.remove((Integer) newPos);
                        }
                    }
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }
}
