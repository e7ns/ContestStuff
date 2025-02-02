package Solved;
// cancer problem, will get stuck at 12/30
// infer the last mapping if 26/27 alphabet mappings are known
import java.io.*;
import java.util.*;

public class ccc06s2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String a = sc.nextLine();
        String b = sc.nextLine();
        String c = sc.nextLine();
        StringBuilder d = new StringBuilder();
        ArrayList<Character> e = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '));
        ArrayList<Character> f = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '));
        HashMap<Character, Character> cipher = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            if (!cipher.containsKey(b.charAt(i))) {
                cipher.put(b.charAt(i), a.charAt(i));
                f.remove((Character) b.charAt(i));
                e.remove((Character) a.charAt(i));
            }
        }
        if (e.size() == 1) {
            cipher.put(f.getFirst(), e.getFirst());
        }
        for (int i = 0; i < c.length(); i++) {
            d.append(cipher.getOrDefault(c.charAt(i), '.'));
        }
        System.out.println(d);
    }
}