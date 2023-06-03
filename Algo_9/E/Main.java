package E;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static class Items{
        int value;
        int weight;
        public Items(int v, int w){
            this.value = v;
            this.weight = w;
        } 
    }
    static int solve(int[]arr , int W, int N){
        ArrayList<Items> items = new ArrayList<>();
        int[][] tables = new int[N+1][W+1];
        items.add(new Items(0, 0));
        for(int i = 0; i < arr.length; i += 2){
            items.add(new Items(arr[i], arr[i+1]));
        }
        for(int i = 1; i <= N; i++){
            for(int x = 1; x <= W; x++){
                if(items.get(i).weight > x) tables[i][x] = tables[i-1][x];
                else tables[i][x] = Math.max(tables[i-1][x], tables[i-1][x-items.get(i).weight]+items.get(i).value);
            }
        }
        return tables[N][W];
    }
    static void devideArr(int[] arr){

    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i = 0; i < T; i++){
            int W = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            int[] X = new int[N*2];
            int iter = 0;
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            System.out.println(solve(X, W, N));
        }
        sc.close();
    }
}
