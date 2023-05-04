package Algo_5_C;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// a< b> a< b> a< b>
public class Main {
    public static void swp(int[] A, int x, int y) {
        if(x == y) return;
        int tmp = A[y];
        A[y] = A[x];
        A[x] = tmp;
    }

    public static int randomPivotslt(int[] A, int lo, int hi) {
        // 랜덤 피벗 생성기
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return rand.nextInt(lo,hi+1);
    }

    public static void rselect(int A[], int lo, int hi, int i) {
        if (lo == hi)
            return ;
        int pivotLoc = partition(A, lo, hi);
        if(pivotLoc == i) return;
        else if(pivotLoc > i) rselect(A, lo, pivotLoc - 1, i);
        else rselect(A, pivotLoc+1, hi, i);
    }
    public static int partition(int[] A, int lo, int hi){
        int randomLoc = randomPivotslt(A, lo, hi);
        swp(A, lo, randomLoc);
        int pivotLoc = mainAlgo(A, lo + 1, hi, A[lo]);
        swp(A, lo, pivotLoc);
        return pivotLoc;
    }
    public static int mainAlgo(int[] A, int lo, int hi, int pivot) {
        // 파티션을 나누는 함수
        while (lo <= hi) {
            while (lo <= hi && pivot >= A[lo])
                lo++;
            while (lo <= hi && pivot < A[hi])
                hi--;
            if (lo < hi) {
                swp(A, lo++, hi--);
            }
        }

        return hi;
    }
    
    public static void solve(int[] arr) {
        int mid = (arr.length - 1) / 2;
        rselect(arr, 0, arr.length - 1, mid);

        int middle = arr[mid];
        int[] arr2 = arr.clone();
        int lo = 0;
        int hi = mid - 1;
        while (lo <= hi) {
            if (arr2[lo] == middle)
                swp(arr2, lo, hi--);
            else
                lo++;
        }
        lo = mid + 1;
        hi = arr2.length - 1;
        while (lo <= hi) {
            if (arr2[hi] == middle)
                swp(arr2, lo++, hi);
            else
                hi--;
        }
        for (int lowe = mid, itera = 0; lowe >= 0; lowe--, itera += 2)
            arr[itera] = arr2[lowe];
        for (int highe = arr.length - 1, itera = 1; highe > mid; highe--, itera += 2)
            arr[itera] = arr2[highe];

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        int N;
        StringTokenizer strTmp;
        int[] arr;
        int iter = 0;
        
        for (int i = 0; i < T; i++) {
            iter = 0;
            N = sc.nextInt();
            System.out.println(N);
            sc.nextLine();
            arr = new int[N];
            strTmp = new StringTokenizer(sc.nextLine(), " ");
            while (strTmp.hasMoreTokens()) {
                arr[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            solve(arr);
            System.out.println(IntStream.of(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        }
        sc.close();
    }
}
