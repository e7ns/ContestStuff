package Unsolved;

import java.util.*;

public class nccc7j5 {
    static TreeSet<Integer> factorize(int n) {
        TreeSet<Integer> out = new TreeSet<>();
        while (n > 1) {
            boolean prime = true;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    n /= i;
                    prime = false;
                    out.add(i);
                    break;
                }
            }
            if (prime) {
                out.add(n);
                break;
            }
        }
        return out;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt();
        int ans = 0;
        for (int i = a; i <= b; i++){
            int temp = d-c+1;
            TreeSet<Integer> factors = factorize(i);
            for (int j = c; j <= d; j++){
                for (int k : factors) {
                    if (j%k == 0){
                        temp--;
                        break;
                    }
                }
            }
            ans += temp;
        }
        System.out.println(ans);
    }
}
