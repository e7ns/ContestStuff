package Solved;

import java.io.*;
import java.util.*;

public class coi10p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int DAY = 24*60*60;
        int[] change = new int[DAY+2]; //DAY+1+1
        int[] time = new int[DAY+1]; //time[1] = 00:00:00
        long[] sumAtTime = new long[DAY+1];
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int hb = Integer.parseInt(s.substring(0,2));
            int mb = Integer.parseInt(s.substring(3,5));
            int sb = Integer.parseInt(s.substring(6,8));
            int he = Integer.parseInt(s.substring(11,13));
            int me = Integer.parseInt(s.substring(14,16));
            int se = Integer.parseInt(s.substring(17,19));
            int begin = hb*60*60 + mb*60 + sb + 1;
            int end = he*60*60 + me*60 + se + 1;
//            System.out.println(begin + " " + end);
            change[begin]++;
            change[end+1]--;
            if (begin > end) {
                change[DAY + 1]--;
                change[1]++;
            }
        }
        int counter = 0;
        for (int i = 1; i <= DAY; i++) {
            counter += change[i];
            time[i] = counter;
            sumAtTime[i] = sumAtTime[i-1] + time[i];
        }
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            String s = br.readLine();
            int hb = Integer.parseInt(s.substring(0,2));
            int mb = Integer.parseInt(s.substring(3,5));
            int sb = Integer.parseInt(s.substring(6,8));
            int he = Integer.parseInt(s.substring(11,13));
            int me = Integer.parseInt(s.substring(14,16));
            int se = Integer.parseInt(s.substring(17,19));
            int begin = hb*60*60 + mb*60 + sb + 1;
            int end = he*60*60 + me*60 + se + 1;
//            System.out.println(begin + " " + end);
            double ans = 0;
            if (begin <= end) {
                ans = sumAtTime[end] - sumAtTime[begin - 1];
                ans /= (end - begin + 1);
            } else {
                ans += sumAtTime[end] - sumAtTime[0];
                ans += sumAtTime[DAY] - sumAtTime[begin-1];
                ans /= (end - begin + DAY + 1);
            }
            System.out.println(ans);
        }
    }
}
