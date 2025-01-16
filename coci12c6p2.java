import java.io.*;
import java.util.*;

public class coci12c6p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] sums = new int[n][n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i < n-1; i++) {
            ans[i] = (sums[i][i+1]-sums[i-1][i+1]+sums[i-1][i])/2;
        }
        if (n == 2){
            ans[0] = ans[1] = sums[0][1]/2;
        } else {
            ans[0] = sums[0][1]-ans[1];
            ans[n-1] = sums[n-2][n-1]-ans[n-2];
        }
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
