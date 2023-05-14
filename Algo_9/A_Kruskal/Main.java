package Algo_9.A_Kruskal;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int getParent(int[] parent, int x){
        if(parent[x]==x){
            return x;
        }
        else return getParent(parent, parent[x]);
    }
    static void union(int[] parent, int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);

        if(a < b){
            parent[a] = b;
        }
        else{
            parent[b] = a;
        }
    }
    static void solve(int[] arr, int edgeNum, int nodeNum){
        int[][] graph = new int[edgeNum][3];
        int[] parent = new int[nodeNum];
        int sum = 0;
        divArr(arr,graph,parent, edgeNum, nodeNum);
        for(int i = 0; i < graph.length; i++){
            if(getParent(parent, graph[i][0]) == getParent(parent, graph[i][1])) continue;
            union(parent, graph[i][0], graph[i][1]);
            sum += graph[i][2];
        }
        System.out.println(sum);
    }
    static void divArr(int[] arr, int[][]graph,int[] parent, int edgeNum, int nodeNum){
        int iter =0;
        for(int i = 0; i < arr.length; i+= 3){
            graph[iter][0] = arr[i];
            graph[iter][1] = arr[i+1];
            graph[iter++][2] = arr[i+2];
        }
        Arrays.sort(graph, (a,b) -> a[2] - b[2]);
        for(int i = 0; i < nodeNum; i++){
            parent[i] = i; 
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int E = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int[] X = new int[E*3];
            if(E == 0) {
                System.out.println("0");
                continue;
            }
            int iter = 0;
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            solve(X, E, N);
        }
        sc.close();
    }
}
