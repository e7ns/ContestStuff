package Solved;

import java.util.Scanner;

public class gcj14qrb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            double c = sc.nextDouble();
            double f = sc.nextDouble();
            double x = sc.nextDouble();
            double n=1, y=0;

            double ans = x/2;

            while (true) {
                y += c/(2.0+(n-1.0)*f);
                double temp = x/(2.0+n*f);
                if (temp+y < ans) {
                    ans = temp+y;
                } else {
                    break;
                }
                n++;
            }
            System.out.println("Case #"+i+": "+ans);
        }
    }
}
