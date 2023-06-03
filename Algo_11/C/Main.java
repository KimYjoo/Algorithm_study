package Algo_11.C;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class Main {
    static final int INF = 987654321;
    public static void divideArr(ArrayList<Integer> arr, int[][] table, int N, int E){
        for(int i = 0; i < N; i++) {
            Arrays.fill(table[i], INF);
            table[i][i] = 0;
        }

        for(int i = 0; i < arr.size(); i++){
            table[arr.get(i)][arr.get(++i)] = arr.get(++i);
        }
         
    }
    public static boolean Floyd(int[][] table, int N){
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                //if(i==k) continue;
                for(int j = 0; j < N; j++){
                    //if(j==k) continue;
                    if(table[i][k] == INF || table[k][j] == INF ) continue;
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }
        for(int j = 0; j < N; j++){
            for(int i = 0; i < N; i++)
                if(table[i][j] + table[j][i]<0) return false;
        }
         
        return true;
    }
    public static void solve(ArrayList<Integer> arr, int N, int E){
        int[][] table = new int[N][N];
        divideArr(arr, table, N, E);
        int max = -987654321;
        if(Floyd(table, N)){
            for(int[] i : table){
                for(int j : i){
                    if(j > 900000 || j < -900000) continue;
                    max = Math.max(max, j);
                }
            }
            for1 : for(int i = 0; i < N; i++){
                for(int j = 0;  j< N; j++){ 
                    if(table[i][j] == max) {
                        System.out.print(i+" "+j+" ");
                        break for1;
                    }
                     
                }
            }
             
        }
        else max = -1;
        System.out.println(max);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i =0; i< T; i++){
            int N = sc.nextInt();
            int E = sc.nextInt();
            sc.nextLine();
            ArrayList<Integer> X = new ArrayList<>();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            while(str.hasMoreTokens()){
                X.add(Integer.parseInt(str.nextToken()));
            }
            solve(X, N, E);
        }
    }
}