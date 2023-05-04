package Algo_7_D;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 987654321;
    public static class Adrs{
        int x;
        int y;

        public Adrs(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static class Node{
        int weight;
        int weight_Sum = INF;
        public Node(int weight){
            this.weight = weight;
        }
        public void setWeightSum(int x){
            this.weight_Sum = x;
        }
    }
    public static void solve(int[][] proc, int M, int N){
        BFS(proc, M, N);
        System.out.println(proc[N-1][M-1]);
    }
    public static void BFS(int[][] proc, int M, int N){
        Queue<Adrs> nodeQ = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int nowX = 0;
        int nowY = 0;
        nodeQ.add(new Adrs(nowX, nowY));
        while(!nodeQ.isEmpty()){
            Adrs tmp = nodeQ.poll();
            nowX = tmp.x;
            nowY = tmp.y;
            if(visited[nowY][nowX]) continue;
            visited[nowY][nowX] = true;
            int left = INF;
            int aBove = INF;
            if(nowX==0 && nowY==0) left = 0;
            else{
                if(nowX - 1 >= 0){
                    left = proc[nowY][nowX-1];
                }
                if(nowY - 1 >= 0){
                    aBove = proc[nowY-1][nowX];
                }
            }
            int lower = left < aBove ? left : aBove;
            proc[nowY][nowX] += lower;
            if(nowX + 1 < M && nowY < N){
                nodeQ.add(new Adrs(nowX + 1, nowY));
            }
            if(nowY + 1 < N && nowX < M){
                nodeQ.add(new Adrs(nowX , nowY+ 1 ));
            } 
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] proc = new int[N][M];
            for(int k = 0; k < N; k++){
                for(int j = 0; j < M; j++){
                    proc[k][j] = sc.nextInt();
                }
            }
            if(M == 1 && N == 1){
                System.out.println(proc[0][0]);
                continue;
            }
            solve(proc, M, N);
        }
    }
}
