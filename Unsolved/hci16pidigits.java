package Unsolved;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;

public class hci16pidigits {

    public static class Triple {
        public final BigInteger p;
        public final BigInteger q;
        public final BigInteger r;

        public Triple(BigInteger p, BigInteger q, BigInteger r) {
            this.p = p;
            this.q = q;
            this.r = r;
        }
    }

    /**
     * If b == a+1:
     *    Pab = -(6*a - 5)·(2*a - 1)·(6*a - 1)
     *    Qab = 10939058860032000 * a³
     *    Rab = Pab * (545140134*a + 13591409)
     *
     * Otherwise, split at m = (a+b)/2:
     *    Pab = Pam * Pmb
     *    Qab = Qam * Qmb
     *    Rab = Qmb * Ram + Pam * Rmb
     */
    public static Triple binarySplit(int a, int b) {
        if (b == a + 1) {
            BigInteger aBI = BigInteger.valueOf(a);
            // Pab = -(6*a - 5)*(2*a - 1)*(6*a - 1)
            BigInteger term1 = BigInteger.valueOf(6L * a - 5);
            BigInteger term2 = BigInteger.valueOf(2L * a - 1);
            BigInteger term3 = BigInteger.valueOf(6L * a - 1);
            BigInteger Pab = term1.multiply(term2).multiply(term3).negate();

            // Qab = 10939058860032000 * a^3
            BigInteger Qab = new BigInteger("10939058860032000").multiply(aBI.pow(3));

            // Rab = Pab * (545140134*a + 13591409)
            BigInteger coeff = BigInteger.valueOf(545140134L).multiply(aBI)
                    .add(BigInteger.valueOf(13591409L));
            BigInteger Rab = Pab.multiply(coeff);

            return new Triple(Pab, Qab, Rab);
        } else {
            int m = (a + b) / 2;
            Triple left = binarySplit(a, m);
            Triple right = binarySplit(m, b);

            BigInteger Pab = left.p.multiply(right.p);
            BigInteger Qab = left.q.multiply(right.q);
            BigInteger Rab = right.q.multiply(left.r).add(left.p.multiply(right.r));
            return new Triple(Pab, Qab, Rab);
        }
    }

    public static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()), mc);
        BigDecimal two = BigDecimal.valueOf(2);
        for (int i = 0; i < mc.getPrecision() + 10; i++) {
            x = x.add(value.divide(x, mc)).divide(two, mc);
        }
        return x;
    }

    public static BigDecimal chudnovsky(int n, MathContext mc) {
        Triple t = binarySplit(1, n);
        BigDecimal Q = new BigDecimal(t.q);
        BigDecimal R = new BigDecimal(t.r);
        BigDecimal multiplier = new BigDecimal("426880").multiply(sqrt(new BigDecimal("10005"), mc), mc);
        BigDecimal numerator = multiplier.multiply(Q, mc);
        BigDecimal denominator = new BigDecimal("13591409").multiply(Q, mc).add(R, mc);
        return numerator.divide(denominator, mc);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        MathContext mc = new MathContext(n+5);
        System.out.println("3"+chudnovsky(n, mc).toString().substring(2, n+1));
    }
}
