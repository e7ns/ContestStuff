package Solved;

import java.io.*;
import java.util.*;

public class _2dperm_5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] ans = new int[N*M+1];
        int[] dif = new int[N*M+2];
        int temp = 0;
        for (int i = 1; i <=N; i++) {
            for (int j = 1; j <=M; j++) {
                int min = i*j;
                int max = N*M-(N-i+1)*(M-j+1)+1;
                dif[min]++;
                dif[max+1]--;
            }
        }
        for (int i = 1; i <=N*M; i++) {
            temp+=dif[i];
            ans[i] = temp;
        }

        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            System.out.println(ans[Integer.parseInt(st.nextToken())]);
        }
    }
}
