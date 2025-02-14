import java.io.*;
import java.util.*;

public class ccc13s3 {
    static class State {
        int[] scores;
        ArrayList<int[]> games;
        State(int[] scores, ArrayList<int[]> games) {
            this.scores = scores;
            this.games = games;
        }
    }
    static int t;
    static boolean win(int[] scores) {
        for (int i = 1; i <= 4; i++) {
            if (i != t && scores[i] >= scores[t]) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        int g = Integer.parseInt(br.readLine());
        ArrayList<int[]> games = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            for (int j = i+1; j <= 4; j++) games.add(new int[]{i,j});
        }
        int[] scores = new int[5];
        for (int i = 0; i < g; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] c = {a,b};
            int sa = Integer.parseInt(st.nextToken());
            int sb = Integer.parseInt(st.nextToken());
            for (int j = 0; j < games.size(); j++) {
                if (Arrays.equals(games.get(j), c)) {
                    games.remove(j);
                    break;
                }
            }
            if (sa > sb) {
                scores[a] += 3;
            } else if (sa < sb) {
                scores[b] += 3;
            } else {
                scores[a]++;
                scores[b]++;
            }
        }
        int ans = 0;
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(Arrays.copyOf(scores, scores.length), new ArrayList<>(games)));
        while (!q.isEmpty()) {
            State cur = q.poll();
            if (cur.games.isEmpty()) {
                if (win(cur.scores)) ans++;
                continue;
            }
            int[] nextGame = cur.games.get(0);
            int a = nextGame[0];
            int b = nextGame[1];

            ArrayList<int[]> remainingGames = new ArrayList<>(cur.games);
            remainingGames.remove(0);

            int[] newScoresA = Arrays.copyOf(cur.scores, cur.scores.length);
            newScoresA[a] += 3;
            q.add(new State(newScoresA, new ArrayList<>(remainingGames)));

            int[] newScoresB = Arrays.copyOf(cur.scores, cur.scores.length);
            newScoresB[b] += 3;
            q.add(new State(newScoresB, new ArrayList<>(remainingGames)));

            int[] newScoresTie = Arrays.copyOf(cur.scores, cur.scores.length);
            newScoresTie[a]++;
            newScoresTie[b]++;
            q.add(new State(newScoresTie, new ArrayList<>(remainingGames)));
        }
        System.out.println(ans);

    }
}
