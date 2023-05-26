package Algo_11.B_pro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class Main {
    public static int dfs(int[] arr, boolean[] visited, int w, int n){
        if(n == arr.length){
            int sum = 0;
            for(int i = 0; i < arr.length; i++) if(visited[i]) sum += arr[i];
            if(sum == w) return 1;
        }
        else{
            int count = 0;
            visited[n] = true;
            count += dfs(arr, visited, w, n+1);
            visited[n] = false;
            count += dfs(arr, visited, w, n+1);
            return count;
        }
        return 0;
    }
    public static void solve(int[] arr, int w, int n){
        int allSum = Arrays.stream(arr).sum();
        boolean[] visited = new boolean[arr.length];
        if((allSum+w)%2 != 0) System.out.println(0);
        int W = (allSum+w)/2;
        dfs(arr, visited,W, n);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int W = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int[] X = new int[N];
            int iter = 0;
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            solve(X, W, N);
        }
        sc.close();
    }
}