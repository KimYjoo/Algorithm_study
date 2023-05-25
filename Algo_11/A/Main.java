package Algo_11.A;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static int solve(String x, String y, int g, int m){
        int x_len = x.length()+1;
        int y_len = y.length()+1;
        int[][] table = new int[x_len][y_len];

        for(int i = 0; i < x_len; i++){
            table[i][0] = i * g;
        }

        for(int i = 0; i < y_len; i++){
            table[0][i] = i * g;
        }

        for(int i = 1; i < x_len; i++){
            for(int j = 1; j < y_len; j++){
                int p = x.charAt(i-1) == y.charAt(j-1) ? 0 : m;
                table[i][j] = Math.min(Math.min(p + table[i-1][j-1], g + table[i-1][j]), g + table[i][j-1]);
            }
        }
        return table[x.length()][y.length()];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < T; i++){
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int G = Integer.parseInt(str.nextToken());
            int M = Integer.parseInt(str.nextToken());
            String X = str.nextToken();
            String Y = str.nextToken();
            System.out.println(solve(X, Y, G, M));
        }   
    }
}