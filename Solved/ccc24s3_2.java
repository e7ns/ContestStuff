package Solved;

import java.io.*;
import java.util.*;

public class ccc24s3_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer sta = new StringTokenizer(br.readLine());
        StringTokenizer stb = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(sta.nextToken());
        }
        boolean valid = false;
        int min = -1;
        int p1 = -1;
        int loc = -1;
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(stb.nextToken());
            if (i == 0 || i == n-1 || b[i] != b[i - 1]) { // check if new move
                //pretend i is one more if its the last
                if (i == n-1) i++;
                // add move if exists
                if (loc != -1) {
                    if (loc < i-1 && loc > p1) {
                        moves.add("R "+loc+" "+(i-1));
                        moves.add("L "+p1+" "+loc);
                    }
                    if (loc <= p1) {
                        moves.add("R "+loc+" "+(i-1));
                    } else {
                        moves.add("L "+p1+" "+loc);
                    }
                }

                p1 = i;
                if (i == n) i--;
                valid = false;
                for (int j = loc + 1; j < n; j++) {
                    if (b[i] == a[j]) {
                        valid = true;
                        loc = j;
                        break;
                    }
                }
                if (!valid) {
                    break;
                }
                // update p1
                //
            }
        }
        if (valid) {
            System.out.println("YES");
            System.out.println(moves.size());
            for (String move : moves) {
                System.out.println(move);
            }
        } else {
            System.out.println("NO");
        }

    }
}
