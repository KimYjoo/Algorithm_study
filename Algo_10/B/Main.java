package Algo_10.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static void cansum(int[] arr , int M){
        HashMap<Integer,Boolean> memo = new HashMap<>();
        ArrayList<Integer> temp = new ArrayList<>();
        if(cs(arr, M, memo, temp)) {
            Collections.sort(temp);
            System.out.print(temp.size()+" ");
            for(int i : temp)System.out.print(i+" ");
            System.out.println("");
        }
        else System.out.println("-1");
    }
    static boolean cs(int[] arr, int M, HashMap<Integer,Boolean> memo, ArrayList<Integer> temp){
        if(M < 0) return false;
        if(M == 0) return true;
        if(memo.containsKey(M)) return memo.get(M);
        for(int i = 0; i < arr.length; i++){
            temp.add(arr[i]);
            if(cs(arr, M-arr[i], memo,temp)){memo.put(M,true); return true;}
            temp.remove(temp.size()-1);
            memo.put(M, false);
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < T; i++){
            int M = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[N];
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int iter = 0;
            while(str.hasMoreTokens()){
                arr[iter++] = Integer.parseInt(str.nextToken());
            }
            
            if(M < Arrays.stream(arr).min().getAsInt()) System.out.println(-1);
            else cansum(arr, M);
            
        }
        sc.close();
    }
}
