package Solved;//too hard to solve, come back later
import java.io.*;
import java.util.*;

public class bts24p5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] blames = new int[N+1];
        ArrayList<ArrayList<Integer>> pos = new ArrayList<>(N+1);
        for (int i = 0; i < N+1; i++){
            pos.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++){
            blames[i] = Integer.parseInt(st.nextToken());
            pos.get(blames[i]).add(i);
        }
        for (int i = 0; i < Q; i++){
            int[] counts = new int[N];
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            boolean flag = true;
            for (int j = l-1; j < r; j++){
                counts[blames[j]-1]++;
                if (counts[blames[j]-1] == k){
                    System.out.println(blames[j]);
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println("-1");
            }
        }
    }
}