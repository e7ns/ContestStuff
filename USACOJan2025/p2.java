package USACOJan2025;
// wrong because moo[1] and moo[2] do not have to start beside each other
import java.io.*;
import java.util.*;

public class p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[] uniquesAt = new int[n];
        long ans = 0;
        //K: Repeated number value, V: Last occurence
        HashMap<Integer, Integer> consec = new HashMap<>();
        HashSet<Integer> unique = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums[0] = Integer.parseInt(st.nextToken());
        unique.add(nums[0]);
        uniquesAt[0] = 1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if (!unique.contains(nums[i])) {
                count++;
                unique.add(nums[i]);
            }
            uniquesAt[i] = count;
            if (nums[i] == nums[i - 1]) {
                consec.put(nums[i], i);
            }
        }
        for (int key : consec.keySet()) {
            ans = ans + uniquesAt[consec.get(key)] - 1;
        }
        System.out.println(ans);
    }
}
