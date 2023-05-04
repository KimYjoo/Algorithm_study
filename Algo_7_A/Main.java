package Algo_7_A;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void makeProc(int[]arrMain, int[][] proc){
        for(int i = 0; i < arrMain.length; i+=2){
            proc[arrMain[i]][arrMain[i+1]] = 1;
            proc[arrMain[i+1]][arrMain[i]] = 1;
        }
    }
    public static void findGolib(int[][]proc, int start, int nodes){
        boolean[] visited = new boolean[nodes];
        Arrays.fill(visited, false);
        Queue<Integer> nodeQ = new LinkedList<>();
        int countGo = 0;
        int countMaxNd = 1;
        int maxNd = 0;
        for(int last = 0; last < nodes; last++){
            if(!visited[last]){
                start = last;
                nodeQ.add(start);
                visited[start] = true;
                while (!nodeQ.isEmpty()) {
                    start = nodeQ.poll();
                    for (int x = 0; x < nodes; x++) {
                        if (!visited[x]&&proc[start][x] == 1) {
                            visited[x] = true;
                            nodeQ.add(x); 
                            countMaxNd++;
                        }
                    }
                }
                if(maxNd < countMaxNd) maxNd = countMaxNd;
                countMaxNd = 1;
                countGo++;
            }
        }
        System.out.println(countGo + " " + maxNd);
    }
    public static void solve(int[] arrMain, int nodes){
        int[][] proc = new int[nodes][nodes];
        makeProc(arrMain, proc);
        findGolib(proc, 0, nodes);
        
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int E = sc.nextInt();
            sc.nextLine();
            StringTokenizer strTmp = new StringTokenizer(sc.nextLine(), " ");
            int[] X = new int[E*2];
            int iter = 0;
            while(strTmp.hasMoreTokens()){
                X[iter++] = Integer.parseInt(strTmp.nextToken());
            }

            solve(X, N);
        }
        sc.close();
    }
}
