import java.io.*;
import java.util.*;

public class test {
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
    public static void main(String[] args) throws IOException {
        System.out.println(factorize(7));
    }
}