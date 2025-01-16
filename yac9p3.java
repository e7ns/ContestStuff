import java.io.*;
import java.util.*;

public class yac9p3 {
    static int check(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(0);
        for (int j : arr) {
            nums.add(j);
        }
        while (!nums.isEmpty()) {
            for (int i = 1; i < nums.size(); i++) {
                if (nums.get(i) % i == 0) {
                    nums.set(nums.get(i)/i, 1);
                }
            }
            if (nums.contains(1)) {
                nums.remove((Integer) 1);
            } else {
                return 0;
            }
        }
        return 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long ans = 0;
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans += check(Arrays.copyOfRange(arr, i, j));
            }
        }
    }
}
