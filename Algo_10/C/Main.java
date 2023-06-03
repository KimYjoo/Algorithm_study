package C;

// 2019136030 / 김용주/ C번
import java.util.Scanner;
import java.util.StringTokenizer;
  
public class Main {
    static void countsum(int[] arr , int M){
        long[] table = new long[M+1];
        for(int i : arr){
            if(i <= M) table[i] = 1;
        }
 
        for(int i = 0; i < M; i++){
            if(table[i] > 0){
                for(int j : arr){
                    //System.out.println("i : "+i+" j : "+j+" i+j : "+(i+j));
 
                    if(i+j <= M ){
                        table[i+j] += table[i];
                    }
                }
            }
        }
        System.out.println(table[M]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < T; i++){
            int M = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[N];
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int iter = 0;
            while(str.hasMoreTokens()){
                arr[iter++] = Integer.parseInt(str.nextToken());
            }
              
            if(M == 0) System.out.println(1);
            else countsum(arr, M);
               
        }
        sc.close();
    }
}