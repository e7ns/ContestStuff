package Solved;

import java.io.*;
import java.util.*;

public class coci18c4p3 {
    static class Dim {
        int x;
        int y;
        Dim(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Dim> incHeight = new PriorityQueue<>(Comparator.comparingInt(a -> a.y));
        PriorityQueue<Dim> decWidth = new PriorityQueue<>((a, b) -> b.x - a.x);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            incHeight.add(new Dim(a, b));
        }
        long width = 0;
        long minArea = Long.MAX_VALUE;
        while (!incHeight.isEmpty()) {
            Dim adding = incHeight.poll();
            width += adding.x;
            decWidth.add(adding);
            if (decWidth.size() > k) {
                Dim removing = decWidth.poll();
                width -= removing.x;
            }
            if (decWidth.size() == k) {
                long area = width * (long) adding.y;
                minArea = Math.min(minArea, area);
            }
        }
        System.out.println(minArea);
    }
}
