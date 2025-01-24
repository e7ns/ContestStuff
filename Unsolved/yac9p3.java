package Unsolved;

import java.io.*;
import java.util.*;

public class yac9p3 {
    static int maxP(int n){
        int p = 2;
        if (n == 1) return 1;
        while (n > 1) {
            boolean isPrime = true;
            for (int i = p; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    n /= i;
                    isPrime = false;
                    if (i > p) {
                        p = i;
                    }
                    break;
                }
            }
            if (isPrime) {
                p = n;
                break;
            }
        }
        return p;
    }
    //first element in subarray must be 1, or else first element cannot be deleted
    //keep track of all instances of 1 in the original array
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long ans = 0;
        int[] A = new int[n];
        ArrayList<Integer> ones = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] == 1) {
                ones.add(i);
            }
        }
        for (int i : ones) {
            for (int j = i; j < n; j++) {
                int val = A[j];
                int pos = j-i+1;
                if (maxP(val) > pos){
                    break;
                } else {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
