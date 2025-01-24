package Solved;

import java.io.*;
import java.util.*;

public class ccc06s3 {
    static boolean intersect(int[] a, int[] b, int[] c, int[] d) {
        int pa = dir(c, d, a);
        int pb = dir(c, d, b);
        int pc = dir(a, b, c);
        int pd = dir(a, b, d);
        if (pa*pb < 0 && pc*pd < 0) {
            return true;
        } else if ((pa == 0 && boxed(c, d, a))
        || (pb == 0 && boxed(c, d, b))
        || (pc == 0 && boxed(a, b, c))
        || (pd == 0 && boxed(a, b, d))) {
            return true;
        }
        return false;
    }
    static int dir(int[] a, int[] b, int[] c) {
        //cross product of vectors AB and AC
        return(b[0]-a[0])*(c[1]-a[1])-(b[1]-a[1])*(c[0]-a[0]);
    }
    static boolean boxed(int[] a, int[] b, int[] c) {
        return (c[0] <= Math.max(a[0], b[0])
        && c[0] >= Math.min(a[0], b[0])
        && c[1] <= Math.max(a[1], b[1])
        && c[1] >= Math.min(a[1], b[1]));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        int[] R = new int[2], J = new int[2];
        R[0] = Integer.parseInt(st.nextToken());
        R[1] = Integer.parseInt(st.nextToken());
        J[0] = Integer.parseInt(st.nextToken());
        J[1] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            ArrayList<int[]> shape = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t; j++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                shape.add(new int[]{x,y});
            }
            shape.add(shape.getFirst());
            for (int j = 0; j < t; j++) {
                if (intersect(shape.get(j), shape.get(j+1), R, J)){
                    ans++;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}
