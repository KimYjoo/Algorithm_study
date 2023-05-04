package Algo_6_C;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
public class Main {
    public static int randP(int lo, int hi){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return rand.nextInt(lo, hi+1);
    }
    public static void swap(int[] arrMain, int x, int y){
        int tmp = arrMain[x];
        arrMain[x] = arrMain[y];
        arrMain[y] = tmp;
    }
    public static int partiLogic(int[] arrMain, int L, int R, int pivot){
        while(L <= R){
            while(L <= R && arrMain[L] < arrMain[pivot]) L++;
            while(L <= R && arrMain[R] >= arrMain[pivot]) R--;
            if( L <= R ) swap(arrMain, L++, R--);
        }
        return R;
    }
    public static int partition(int[] arrMain, int lo, int hi){
        int hiLoc = 0;
        int pivot = randP(lo, hi);
        swap(arrMain, lo, pivot);
        hiLoc = partiLogic(arrMain, lo+1, hi, lo);
        swap(arrMain, lo, hiLoc);

        return hiLoc;
    }
    public static void rMain(int[] arrMain, int lo, int hi, int i){
        if(lo == hi) return;
        int pivotLoc = partition(arrMain, lo, hi);
        if(pivotLoc == i) return;
        else if(pivotLoc > i) rMain(arrMain, lo, pivotLoc - 1, i);
        else rMain(arrMain, pivotLoc+1, hi, i);
        return;
    }
    public static void rselect(int[] arrMain, int i){
        rMain(arrMain, 0, arrMain.length-1, i);
    }
    public static void solving(int[] arrMain, int k){
        Map <Integer, Integer> tmp = new HashMap<>();
        for(int t : arrMain){
            if(tmp.containsKey(t)){
                tmp.replace(t,tmp.get(t)+1);
            }
            else{
                tmp.put(t,1);
            }
        }
        
        for()
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 1;//sc.nextInt();
        //sc.nextLine();
        for(int i = 0; i < T; i++){
            //String[] tmp = sc.nextLine().split(" ");
            int N = 17;//Integer.parseInt(tmp[0]);
            int K = 3;//Integer.parseInt(tmp[1]);
            StringTokenizer strTmp = new StringTokenizer("8 6 4 9 7 8 3 7 6 9 2 1 9 3 1 7 4"/*sc.nextLine()*/, " ");
            int[] X = new int[N];
            int iter = 0;
            while(strTmp.hasMoreTokens()){
                X[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            for(int iteri : X){
                System.out.print(iteri+" ");
    
            }
            System.out.println("");
            solving(X, K);

        }
    }
}
