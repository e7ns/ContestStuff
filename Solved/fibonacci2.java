package Solved;

import java.math.BigInteger;
import java.io.*;

public class fibonacci2 {
    static long[] res = {0, 1};
    static BigInteger n;
    static void FastDoubling(BigInteger n) {
        long a, b, c, d;
        long MOD = 1000000007;
        if (n.equals(BigInteger.ZERO)) {
            res[0] = 0;
            res[1] = 1;
            return;
        }
        FastDoubling(n.divide(BigInteger.TWO));
        a = res[0];
        b = res[1];
        c = 2 * b - a;
        if (c < 0) c += MOD;
        c = (a * c) % MOD;
        d = (a * a + b * b) % MOD;
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            res[0] = c;
            res[1] = d;
        } else {
            res[0] = d;
            res[1] = c + d;
        }
    }
    static BigInteger pisano(BigInteger n) {
        return n.mod(BigInteger.valueOf(2000000016));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = new BigInteger(br.readLine());
        FastDoubling(pisano(n));
        System.out.println(res[0]);
    }
}
