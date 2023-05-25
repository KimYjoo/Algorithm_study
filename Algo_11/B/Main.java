package Algo_11.B;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static int solveSum(int[] arr, int iter, int w, int ret, ArrayList<HashMap<Integer, Integer>> memo){
        if(ret > w) return 0;
        if(iter == arr.length){
            if(ret == w) return 1;
            else return 0;
        }
        if(memo.get(iter).containsKey(ret)) return memo.get(iter).get(ret);
        int count1 = solveSum(arr, iter+1, w, ret+arr[iter], memo);
        int count2 = solveSum(arr, iter+1, w, ret-arr[iter], memo);
        memo.get(iter).put(ret, count1 + count2);
        System.out.println("iter : "+ iter + " ret : " + ret + " value : " + (count1 + count2));
        return count1 + count2;
    }
    public static void solve(int[] arr, int w, int n){
        ArrayList<HashMap<Integer, Integer>> memo = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            memo.add(new HashMap<>());
        }
        System.out.println(solveSum(arr, 0, w, 0, memo));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int W = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int[] X = new int[N];
            int iter = 0;
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            solve(X, W, N);
        }
        sc.close();
    }
}
