import java.io.*;
import java.util.*;
class rts {
    int[] rude;
    int[] seat;
    double dist;
    public rts(int[] r, int[] s, double d){
        rude = r; seat = s; dist = d;
    }
    public int[] getRude() {return rude;}
    public int[] getSeat() {return seat;}
    public double getDist() {return dist;}
    public String toString(){ return Arrays.toString(rude)+" "+Arrays.toString(seat)+" "+dist;}
}
public class coci09c7p3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] tram = new char[r][c];
        ArrayList<int[]> rude = new ArrayList<>();
        ArrayList<int[]> seats = new ArrayList<>();
        ArrayList<rts> rudesToSeats = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                char current = row.charAt(j);
                tram[i][j] = current;
                if (current == 'X') {
                    rude.add(new int[]{i, j});
                } else if (current == 'L') {
                    seats.add(new int[]{i, j});
                }
            }
        }
        for (int[] i : rude) {
            for (int[] j : seats) {
                int x2 = (j[0]-i[0])*(j[0]-i[0]);
                int y2 = (j[1]-i[1])*(j[1]-i[1]);
                double distance = Math.sqrt(x2+y2);
                rudesToSeats.add(new rts(i, j, distance));
            }
        }
        rudesToSeats.sort(Comparator.comparingDouble(rts::getDist).thenComparing((rts a, rts b) ->  Arrays.compare(a.getSeat(), b.getSeat())));
//        for (rts i : rudesToSeats) {
//            System.out.println(i.toString()+"\n");
//        }
        int temp = 1;
        while (rudesToSeats.size() > 1) {
            double distA = rudesToSeats.get(0).getDist();
            double distB = rudesToSeats.get(1).getDist();
            int[] seatA = rudesToSeats.get(0).getSeat();
            int[] seatB = rudesToSeats.get(1).getSeat();
            int[] rudeA = rudesToSeats.get(0).getRude();
            int[] rudeB = rudesToSeats.get(1).getRude();
            if (distA == distB && Arrays.equals(seatA, seatB)) {
                temp++;
            } else {
                if (temp > 1) {
                    ans++;
                    temp = 1;
                }
                for (int i = rudesToSeats.size() - 1; i >= 0; i--) {
                    if (Arrays.equals(rudesToSeats.get(i).getSeat(), seatA)) {
                        rudesToSeats.remove(i);
                    }
                }
            }
            for (int i = rudesToSeats.size() - 1; i >= 0; i--) {
                if (Arrays.equals(rudesToSeats.get(i).getRude(), rudeA)) {
                    rudesToSeats.remove(i);
                }
            }
        }
        if (temp > 1) {
            ans++;
        }
        System.out.println(ans);
    }
}