package Algo_10.A;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static void cansum(int[] arr , int M){
        HashMap<Integer,Boolean> memo = new HashMap<>();
        System.out.println(cs(arr, M, memo));
    }
    static boolean cs(int[] arr, int M, HashMap<Integer,Boolean> memo){
        if(M < 0) return false;
        if(M == 0) return true;
        if(memo.containsKey(M)) return memo.get(M);
        for(int i = 0; i < arr.length; i++){
            if(cs(arr, M-arr[i], memo)){memo.put(M,true); return true;}
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
            
            if(M < Arrays.stream(arr).min().getAsInt()) System.out.println(false);
            else cansum(arr, M);
             
        }
        sc.close();
    }
}
