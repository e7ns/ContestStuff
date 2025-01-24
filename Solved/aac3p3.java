package Solved;

import java.io.*;
import java.util.*;

public class aac3p3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] prices = new int[n];
        int[] newprices = new int[n];
        StringBuilder actions = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(prices);
//        System.out.println(Arrays.toString(prices));
        for (int i = 0; i < (n+1)/2; i++) {
            double ratio = prices[n-1-i]*1.0 / prices[i];
            if (ratio > 1) {
                actions.append("BS");
                newprices[2*i] = prices[i];
                newprices[2*i+1] = prices[n-1-i];
            } else if (i == n-1-i) {
                actions.append("E");
                newprices[2*i] = prices[i];
            } else {
                actions.append("EE");
                newprices[2*i] = prices[i];
                newprices[2*i+1] = prices[n-1-i];
            }
        }
        for (int i = 0; i < newprices.length; i++) {
            System.out.print(newprices[i]);
            if (i == newprices.length-1) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println(actions);
    }
}
