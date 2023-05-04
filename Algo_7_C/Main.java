package Algo_7_C;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 987654321;
    public static class Node implements Comparable<Node>{
        int vertex;
        int weight;
        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
    public static void makeProc(int[] road, int N, int[][] proc){
        for(int i = 0; i < road.length; i += 3){
            proc[road[i]][road[i+1]] = road[i+2];
        }
    }
    public static void solve(int[] road, int[] purposeNd, int N, int S){
        int[][] proc = new int[N][N];
        makeProc(road, N, proc);
        Map<Integer, Integer> map = dijkstraAlgo(proc, N, S);
        for(int iter : purposeNd){
            System.out.print((map.get(iter) == INF ? -1 : map.get(iter)) + " ");
        }
        System.out.println();
    }
    public static Map<Integer,Integer> dijkstraAlgo(int[][] proc, int N, int S){
        Map <Integer, Integer> map = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<Integer>  set = new HashSet<>();

        for(int i = 0; i < N; i++){
            if(i == S)map.put(i,0);
            else map.put(i, INF);
        } // 각 노드의 초기 거리값 초기화, 시작 노드는 거리값을 0으로
        pq.add(new Node(S, 0)); // 우선순위 큐에 시작 노드를 삽입 거리는 0으로

        while(!pq.isEmpty()){
            Node tmp = pq.poll();
            int v = tmp.vertex;
            int w = tmp.weight;

            if(set.contains(v)) continue;
            set.add(v);
            for(int i = 0; i < N; i++){
                if(proc[v][i] > 0){
                    int score = map.get(i) < w + proc[v][i] ? map.get(i) : w + proc[v][i];
                    map.replace(i, score);
                    Node ND = new Node(i,score);
                    pq.add(ND);
                }
            }

        }

        return map;

    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int N = sc.nextInt(); // 노드의 수
            int E = sc.nextInt(); // 간선의 수
            int S = sc.nextInt(); // 출발 노드
            int K = sc.nextInt(); // 목적 노드의 수
            int[] purposeNd = new int[K]; // 목적 노드를 담을 배열
            //sc.nextLine();
            StringTokenizer strTmp = new StringTokenizer(sc.nextLine(), " ");
            int iter = 0;
            while(strTmp.hasMoreTokens()){
                purposeNd[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            //for(int j : purposeNd) System.out.println(j);
            int[] X = new int[E*3];  // 간선 정보 ( 시작노드 , 끝 노드 , 가중치 )
            strTmp = new StringTokenizer(sc.nextLine(), " ");
            iter = 0;
            while(strTmp.hasMoreTokens()){
                X[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            if(N == 1){
                System.out.println(0);
                continue;
            }
            solve(X, purposeNd, N, S);
        }
    }
}
