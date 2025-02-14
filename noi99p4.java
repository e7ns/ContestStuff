import java.io.*;
import java.util.*;

public class noi99p4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[8][8];
        for (int i = 0; i < 8; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
    }
}
