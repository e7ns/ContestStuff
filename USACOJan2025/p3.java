package USACOJan2025;

import java.io.*;
import java.util.*;

public class p3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        int[] ans = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st1.nextToken());
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] tempA = Arrays.copyOf(A, n);
                int halflen = (j-i+1)/2;
                int count = 0;
                for (int k = 0; k < halflen; k++) {
                    int temp = tempA[i+k];
                    tempA[i+k] = tempA[j-k];
                    tempA[j-k] = temp;
                }
                for (int k = 0; k < n; k++){
                    if (tempA[k] == B[k]){
                        count++;
                    }
                }
                ans[count]++;
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(ans[i]);
        }
    }
}
