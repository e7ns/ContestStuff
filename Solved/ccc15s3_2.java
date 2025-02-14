package Solved;

import java.io.*;
import java.util.BitSet;
public class ccc15s3_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        BitSet bs = new BitSet(g);
        for (int i = 0; i < p; i++) {
            int n = Integer.parseInt(br.readLine()) - 1;
            int empty = bs.previousClearBit(n);
            if (empty == -1) {
                System.out.println(i);
                return;
            } else {
                bs.set(empty);
            }
        }
        System.out.println(p);
    }
}
