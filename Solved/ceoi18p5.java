package Solved;

import java.io.*;
import java.util.*;

public class ceoi18p5 {
    static HashSet<String> factorizations;
    static TreeSet<Integer> ans;
    static void search(ArrayList<Integer> f) {
        int fsum = 0;
        for (int k : f) {
            fsum += k;
        }
        for (int i = 0; i < f.size(); i++) {
            int c = f.get(i);
            int e = (int) Math.sqrt(c);
            for (int j = 2; j <= e; j++) {
                if (c % j == 0) {
                    ArrayList<Integer> temp = new ArrayList<>(f);
                    temp.remove(i);
                    temp.add(j);
                    temp.add(c / j);
                    Collections.sort(temp);
                    String key = temp.toString();
                    if (!factorizations.contains(key)) {
                        int p = fsum - c + j + c / j - temp.size();
                        ans.add(p);
                        factorizations.add(key);
                        search(temp);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ans = new TreeSet<>();
        ans.add(n-1);
        factorizations = new HashSet<>();
        search(new ArrayList<>(List.of(n)));
        System.out.println(ans.size());
        for (int i : ans){
            System.out.print(i + " ");
        }
    }
}
