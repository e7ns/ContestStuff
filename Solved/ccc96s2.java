package Solved;

import java.io.*;
import java.util.*;
//cancer
//using static int p for looping, cursed
public class ccc96s2 {
    static int p;
    static private class gigaInt {
        int[] num;
        public gigaInt(String a) {
            char[] temp = a.toCharArray();
            num = new int[temp.length];
            for (int i = 0; i < temp.length; i++){
                num[i] = temp[i] - '0';
            }
        }
        public void subtract(int a) {
            int len = num.length;
            int pos = num[len - 1];
            if (pos >= a) {
                num[len-1] = pos-a;
            } else {
                num[len-1] = pos-a+10;
                num[len-2]--;
                for (int i = len - 2; i >= 0; i--) {
                    if (num[i] >= 0) {
                        if (num[0] == 0){
                            num = Arrays.copyOfRange(num, 1, len);
                            p++;
                        }
                        break;
                    } else {
                        num[i] = 9;
                        num[i-1]--;
                    }
                }
            }
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i : num) {
                sb.append(i);
            }
            return sb.toString();
        }
        public int removeLast() {
            int out = num[num.length-1];
            num = Arrays.copyOf(num, num.length-1);
            return out;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int z = 0; z < t; z++) {
            String num = br.readLine();
            gigaInt g = new gigaInt(num);
            for (p = 0; p < num.length()-2; p++) {
                System.out.println(g);
                g.subtract(g.removeLast());
            }
            System.out.println(g);
            int r = Integer.parseInt(g.toString()) % 11;
            System.out.print("The number " + num + " is ");
            if (r != 0) {
                System.out.print("not ");
            }
            System.out.println("divisible by 11.");
            if (z != t-1) {
                System.out.println();
            }
        }
    }
}
