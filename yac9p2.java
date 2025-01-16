import java.io.*;
import java.util.*;

public class yac9p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int z = 0; z < t; z++) {
            int ans = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ArrayList<Integer> piles = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                piles.add(x);
                if (x==1){
                    ans++;
                }
            }

            if (ans % 2 == 1) {
                System.out.println("Josh");
            } else {
                System.out.println("Mike");
            }
        }
    }
}
