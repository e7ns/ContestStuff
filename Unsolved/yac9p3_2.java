package Unsolved;

import java.io.*;
import java.util.*;
//first time using stack!
//ok nvm abandoned
public class yac9p3_2 {
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
        Stack<Integer> blocking = new Stack<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(ans);
    }
}
