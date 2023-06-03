package Algo_11.A;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void solve(String x, String y, int g, int m){
        int[][] table = new int[x.length()+1][y.length()+1];

        for(int i = 0; i <= x.length(); i++){
            table[i][0] = g * i;
        }
        for(int i = 0; i <= y.length(); i++){
            table[0][i] = g * i;
        }
        for(int i = 1; i <= x.length(); i++){
            for(int j = 1; j <= y.length(); j++){
                int p = x.charAt(i-1) == y.charAt(j-1) ? 0 : m;
                table[i][j] = Math.min(Math.min(p+table[i-1][j-1], g + table[i-1][j]), g + table[i][j-1]);
                
            }
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int G = Integer.parseInt(str.nextToken());
            int M = Integer.parseInt(str.nextToken());

            String X = str.nextToken();
            String Y = str.nextToken();
            solve(X, Y, G, M);
        }
    }
}
