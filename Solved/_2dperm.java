package Solved;

import java.util.*;

public class _2dperm {
    static int n;
    static int m;

    static ArrayList<ArrayList<Integer>> counts;
    static ArrayList<ArrayList<int[]>> reached;
    static void stuff(int t, int[] nCount, int[] mCount){
        for (int i = 0; i < n; i++){
            int[] nTemp = Arrays.copyOf(nCount, n);
            int[] mTemp = Arrays.copyOf(mCount, m);
            if ((nTemp[i] < m)&&((i > 0 && nTemp[i] < nTemp[i-1])||(i == 0))){
                nTemp[i]++;
                mTemp[nTemp[i]-1]++;
                int pos = i*m + nTemp[i];
                if (!counts.get(t).contains(pos)){
                    counts.get(t).add(pos);
                }
                ArrayList<int[]> tempList = new ArrayList<>();
                tempList.add(nTemp);
                tempList.add(mTemp);

                if (!reached.contains(tempList) && t+1 < m*n){
                    reached.add(tempList);
                    stuff(t+1, nTemp, mTemp);
                }
                if (nTemp[i] == 0){
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] nCount = new int[n];
        int[] mCount = new int[m];
        int q = sc.nextInt();
        int[] qList = new int[q];
        counts = new ArrayList<>();
        for (int i = 0; i < m*n; i++){
            counts.add(new ArrayList<>());
        }
        reached = new ArrayList<>();
        stuff(0, nCount, mCount);
        System.out.println(counts.toString());
        for (int i = 0; i < q; i++){
            qList[i] = sc.nextInt();
        }
        for (int i : qList){
            System.out.print(counts.get(i-1).size());
        }


    }
}
