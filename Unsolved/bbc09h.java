package Unsolved;

import java.io.*;
import java.util.*;

public class bbc09h {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int xDigit = Integer.SIZE - Integer.numberOfLeadingZeros(x);
        double[] pDigit = new double[xDigit];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= x; i++) {
            double a = Double.parseDouble(st.nextToken());
            for (int j = i; j >= 1; j -= j&-j) {
                int bit = j & -j;
                int bitIndex = Integer.numberOfTrailingZeros(bit);
                pDigit[bitIndex] += a;
            }
        }
        System.out.println(Arrays.toString(pDigit));

    }
}
