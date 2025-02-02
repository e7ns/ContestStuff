package USACOJan2025;

import java.io.*;
import java.util.*;

public class p2_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[] uniquesAt = new int[n];
        long ans = 0;
        //K: Repeated number value, V: Last occurence
        HashMap<Integer, int[]> moo = new HashMap<>();
        HashSet<Integer> unique = new HashSet<>();
        int[] prev = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums[0] = Integer.parseInt(st.nextToken());
        unique.add(nums[0]);
        prev[nums[0]] = 0;
        uniquesAt[0] = 1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if (!unique.contains(nums[i])) {
                count++;
                unique.add(nums[i]);
            } else {
                moo.put(nums[i], new int[]{prev[nums[i]], i});
            }
            prev[nums[i]] = i;
            uniquesAt[i] = count;
        }
        for (int key : moo.keySet()) {
            ans = ans + uniquesAt[moo.get(key)[0]] - 1;
        }
        System.out.println(ans);
    }
}
