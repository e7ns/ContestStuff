package Solved;

import java.io.*;
import java.util.*;
// incorrectly outputs NO for most cases
public class ccc24s3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer sta = new StringTokenizer(br.readLine());
        StringTokenizer stb = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        int[] b = new int[n];
        ArrayList<int[]> difs = new ArrayList<>();
        boolean difopen = false;
        int difstart = 0;
        boolean valid = true;
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(sta.nextToken());
            b[i] = Integer.parseInt(stb.nextToken());
            if (a[i] != b[i]) {
                if (!difopen) {
                    difstart = i;
                    difopen = true;
                }
                if (i == n-1) {
                    difs.add(new int[]{difstart, i});
                    difopen = false;
                }
            } else if (a[i] == b[i] && difopen) {
                difs.add(new int[]{difstart, i-1});
                difopen = false;
            }
        }
        for (int[] dif : difs) {
            if (dif[0] == 0 && dif[1] == n-1) {
                valid = false;
                break;
            }
            boolean l = true;
            boolean r = true;
            if (dif[0] == 0) {
                for (int i = dif[0]; i <= dif[1]; i++) {
                    if (b[i] != b[dif[1]+1]) {
                        l = false;
                        break;
                    }
                }
                if (l) {
                    moves.add("L " + 0 + " " + (dif[1]+1));
                } else {
                    valid = false;
                    break;
                }
            } else if (dif[1] == n-1) {
                for (int i = dif[0]; i <= dif[1]; i++) {
                    if (b[i] != b[dif[0]-1]) {
                        r = false;
                        break;
                    }
                }
                if (r) {
                    moves.add("R " + (dif[0]-1) + " " + dif[1]);
                } else {
                    valid = false;
                    break;
                }
            } else {
                for (int i = dif[0]; i <= dif[1]; i++) {
                    if (b[i] != b[dif[0] - 1]) l = false;
                    if (b[i] != b[dif[1] + 1]) r = false;
                }
                if (!l && !r) {
                    valid = false;
                    break;
                } else if (l) {
                    moves.add("L " + dif[0] + " " + (dif[1] + 1));
                } else {
                    moves.add("R " + (dif[0] - 1) + " " + dif[1]);
                }
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
