package C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static class Node implements Comparable<Node>{
        int x, y;
        public Node( int x, int y){
            this.x =x;
            this.y = y;
        }
        public int compareTo(Node o){
            if(x == o.x) return y - o.y;
            return x - o.x;
        }
    }
    public static void divideArr(int[] arr, int[][] table, int N, int E){
        for(int i = 0; i < N; i++)Arrays.fill(table[i], INF);
        for(int i = 0; i < N; i++) table[i][i] = 0;
        for(int i = 0; i < arr.length; i++){
            table[arr[i]][arr[++i]] = arr[++i];
        }
        
    }
    public static boolean Floyd(int[][] table, int N){
        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                //if(i==k) continue;
                for(int j = 0; j < N; j++){
                    //if(j==k) continue;
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }
        for(int j = 0; j < N; j++){
            if(table[j][j]<0) return false;
        }
        
        return true;
    }
    public static void solve(int[] arr, int N, int E){
        int[][] table = new int[N][N];
        ArrayList<Node> tmp = new ArrayList<>();
        divideArr(arr, table, N, E);
        int max = 0;
        if(Floyd(table, N)){
            for(int[] i : table){
                for(int j : i){
                    if(j > 100 || j < -100) continue;
                    max = Math.max(max, j);
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0;  j< N; j++) if(table[i][j] == max){tmp.add(new Node(i,j));} 
            }
            Collections.sort(tmp);
            System.out.print(tmp.get(0).x+" "+tmp.get(0).y +" ");
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
            int[] X = new int[E*3];
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int iter = 0;
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            solve(X, N, E);

        }
    }
}
