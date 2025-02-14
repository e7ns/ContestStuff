package Solved;

import java.io.*;
import java.util.*;

public class ccc15s3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        TreeSet<Integer> empty = new TreeSet<>();
        for (int i = 1; i <= g; i++) empty.add(i);
        for (int i = 0; i < p; i++) {
            int gi = Integer.parseInt(br.readLine());
            Integer a = empty.floor(gi);
            if (a == null) {
                System.out.println(i);
                return;
            } else {
                empty.remove(a);
            }
        }
        System.out.println(p);
    }
}
