package Solved;

import java.io.*;
import java.util.*;

public class acc7p4 {
    static long[] fact;
    static long mod = 998244353;
    static long binpow(long a, long b) { //pow(a, b) with mod
        b %= (mod-1);
        a %= mod;
        long r = 1;
        while (b > 0) {
            if ((b & 1) == 1) r = r * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return r;
    }
    static long modInv(long a, long m) {
        return binpow(a, m - 2);
    }
    static long nCr(int n, int r) {
        long invR = modInv(fact[r], mod);
        long invNR = modInv(fact[n - r], mod);
        return fact[n] * invR % mod * invNR % mod;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long ans = 0;
        fact = new long[k + 1];
        fact[0] = 1;
        for (int i = 1; i <= k; i++) {
            fact[i] = fact[i - 1] * i % mod;
        }
        for (int i = k; i >= 1; i--) {
            long term = (nCr(k, i) * binpow(i - 1, n - 1)) % mod;
            term = (term * i) % mod;
            if ((k - i) % 2 == 0) {
                ans = (ans + term) % mod;
            } else {
                ans = (ans - term + mod) % mod; // add mod before taking % mod to avoid negatives
            }
        }
        System.out.println((ans % mod + mod) % mod);
    }
}
