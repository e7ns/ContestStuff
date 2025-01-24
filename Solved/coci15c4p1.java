package Solved;

import java.io.*;

public class coci15c4p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        String m = br.readLine();
        boolean nYoda = true;
        boolean mYoda = true;
        int nl = n.length();
        int ml = m.length();
        if (nl > ml) {
            for (int i = 0; i < nl-ml; i++) {
                m = "0" + m;
            }
        } else {
            for (int i = 0; i < ml-nl; i++) {
                n = "0" + n;
            }
        }

        for (int i = Math.max(n.length(), m.length())-1; i >= 0; i--) {
            if (n.charAt(i) > m.charAt(i)) {
                m = m.substring(0, i) + m.substring(i+1);
                nYoda = false;
            } else if (m.charAt(i) > n.charAt(i)) {
                n = n.substring(0, i)+ n.substring(i+1);
                mYoda = false;
            } else {
                nYoda = mYoda = false;
            }
        }
        if (nYoda) {
            System.out.println("YODA");
            System.out.println(Integer.parseInt(m));
        } else if (mYoda) {
            System.out.println(Integer.parseInt(n));
            System.out.println("YODA");
        } else {
            System.out.println(Integer.parseInt(n));
            System.out.println(Integer.parseInt(m));
        }
    }
}
