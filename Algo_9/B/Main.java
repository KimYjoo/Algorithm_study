package Algo_9.B;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static class Edge{
        int num;
        int one, two, value;
        public Edge(int one, int two, int value, int num){
            this.one = one;
            this.two = two;
            this.value = value;
            this.num = num;
        }
    }
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
    static boolean kruskal(ArrayList<Edge> graph, int[] parent, int ignore, int goal){
        int sum = 0;
        for(int i = 0; i < graph.size(); i++){
            if(ignore==i) continue;
            if(getParent(parent, graph.get(i).one) == getParent(parent, graph.get(i).two)) continue;
            union(parent, graph.get(i).one, graph.get(i).two);
            sum += graph.get(i).value;
        }
        if(goal == sum) return false;
        else return true;
    }
    static void solve(int[] arr, int edgeNum, int nodeNum){
        ArrayList<Edge> graph = new ArrayList<>();
        int[] parent = new int[nodeNum];
        ArrayList<Integer> retEdges = new ArrayList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        int sum = 0;
        divArr(arr,graph,parent, edgeNum, nodeNum);
        int[] parent2 = Arrays.copyOf(parent, parent.length);
        for(int i = 0; i < graph.size(); i++){
            if(getParent(parent2, graph.get(i).one) == getParent(parent2, graph.get(i).two)) continue;
            union(parent2, graph.get(i).one, graph.get(i).two);
            retEdges.add(i);
            sum += graph.get(i).value;
        }
        for(int i =0; i < edgeNum; i++){
            if(kruskal(graph, Arrays.copyOf(parent, parent.length), i, sum)) ret.add(graph.get(i).num);
        }
       
        if(ret.isEmpty()) System.out.println("-1");
        else{
            Collections.sort(ret);
            for(int i : ret){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
    static void divArr(int[] arr, ArrayList<Edge> graph,int[] parent, int edgeNum, int nodeNum){
        int iter =0;
        for(int i = 0; i < arr.length; i+= 3){
            graph.add(new Edge(arr[i], arr[i+1],arr[i+2],iter++));
        }
        Collections.sort(graph, (a,b) -> a.value - b.value);
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
