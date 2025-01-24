package Solved;

import java.util.*;
import java.io.*;

public class ceoi18p5_2 {
    // enumerates factorizations in increasing order without storing in hashset
    static TreeSet<Integer> ans = new TreeSet<>();
    static void factorizeAll(int n, int start, int sum, int count) {
        if (n >= start) {
            ans.add(((sum + n) - (count + 1)));
        }
        int e = (int) Math.sqrt(n);
        for (int f = start; f <= e; f++) {
            if (n % f == 0) {
                factorizeAll(n / f, f, sum + f, count + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        factorizeAll(n, 2, 0, 0);
        ans.add(n-1);
        System.out.println(ans.size());
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
