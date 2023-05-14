package Algo_9.A_Prim;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
    static class Node implements Comparable<Node>{
        int node;
        int value;
        public Node(int node, int value){
            this.node = node;
            this.value = value;
        }
        public int compareTo(Node a){
            return value - a.value;
        }
    }


    static void divArr(int[] arr, ArrayList<ArrayList<Node>> nList, int edgeNum, int nodeNum){
        for(int i = 0; i < nodeNum; i++){
            nList.add(new ArrayList<Node>());
        }
        for(int i = 0; i < arr.length; i+=3){
            nList.get(arr[i]).add(new Node(arr[i+1],arr[i+2]));
            nList.get(arr[i+1]).add(new Node(arr[i],arr[i+2]));
        }
        
    }
    static void solve(int[]arr, int nodeNum, int edgeNum, int start){
        boolean[] visited = new boolean[nodeNum];
        ArrayList<ArrayList<Node>> nList = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        divArr(arr, nList, edgeNum, nodeNum);
        int count= 0;
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            Node nowLoc = pq.poll();
            if(visited[nowLoc.node]) continue;
            else visited[nowLoc.node] = true;
            count += nowLoc.value;
            for(Node i : nList.get(nowLoc.node)){
                pq.add(i);
            }
        }
        System.out.println(count);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            int E = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            if(E == 0){
                System.out.println("0");
                continue;
            }
            int[] X = new int[E*3];
            int iter = 0;
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            solve(X, N, E, 0);

        }
        sc.close();
    }
}