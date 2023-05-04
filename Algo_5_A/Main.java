package Algo_5_A;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Main {
    public static void swap(int[] A, int x, int y){
        //두 항목 교환 함수
        if(x == y ) return;
        int tmp = A[x];
        A[x] = A[y];
        A[y] = tmp;
    }
    public static int randomPivotslt(int[] A, int lo, int hi) {
        // 랜덤 피벗 생성기
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return rand.nextInt(lo,hi+1);
    }
    public static int quick_Sort(int[] A, int lo, int hi){
        //빠른 정렬 구현
        if (hi < lo) return 0;
        int p = randomPivotslt(A, lo, hi);
        int pivot = A[p];
        swap(A,p,lo);
        int L = lo + 1, R = hi;
        while(L <= R){
            while( L <= R && A[L] <= pivot) L++;
            while( L <= R &&  A[R] >= pivot) R--;
             
            if( L <= R ) swap(A,L,R);
             
        }
        swap(A,lo,R);
        return R;
    }
    public static void quick_Main(int[] A, int L ,int R){
        int p = 0;
        if(L <= R){
            p = quick_Sort(A, L, R);
            quick_Main(A, L, p-1);
            quick_Main(A, p+1, R);
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        int N = 0;
        String[] str_Tmp;
        for(int i = 0; i < T; i++){
            N = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[N];
            str_Tmp = sc.nextLine().split(" ");
            for(int iter = 0; iter < N; iter++){
                arr[iter] = Integer.parseInt(str_Tmp[iter]);
            }
            quick_Main(arr,0,N-1);
            for(int iter : arr) System.out.print(iter + " ");
            System.out.println();
 
             
        }
    }
}