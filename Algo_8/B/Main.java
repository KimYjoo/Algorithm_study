package Algo_8.B;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
    static void mergesort(int[]a, int[]b, int[] c, int left, int right){
        if(left < right){
            int mid = (left+right)/2;
            mergesort(a, b, c, left, mid);
            mergesort(a, b, c, mid+1, right);
            merge(a,b,c,left,right,mid);
        }
    }
    static void merge(int[]a, int[]b, int[] c, int left, int right, int mid){
        int lo = left, hi = mid+1, i = left;
        int[][] sorted = new int[3][a.length];
        while(lo <= mid && hi <= right){
            if(a[lo] > a[hi]){
                sorted[0][i] = a[lo];
                sorted[1][i] = b[lo];
                sorted[2][i++] = c[lo++];

            }
            else{
                sorted[0][i] = a[hi];
                sorted[1][i] = b[hi];
                sorted[2][i++] = c[hi++];
            }
        }
        if(hi > right){
            while(lo <= mid){
                sorted[0][i] = a[lo];
                sorted[1][i] = b[lo];
                sorted[2][i++] = c[lo++];
            }
        }
        if(lo > mid){
            while(hi <= right){
                sorted[0][i] = a[hi];
                sorted[1][i] = b[hi];
                sorted[2][i++] = c[hi++];
            }
        }

        for(i = left; i <= right; i++){
            a[i] = sorted[0][i];
            b[i] = sorted[1][i];
            c[i] = sorted[2][i];
        }
    }
    static int findmax(int[] arr){
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }
    static void divideArr(int[] mainArr, int[] a, int[] b, int[] id){
        int iter = 0;
        for(int i = 0; i < mainArr.length; i+=2){
            a[iter++] = mainArr[i];
        }
        iter = 0;
        for(int i = 1; i < mainArr.length; i+=2){
            b[iter++] = mainArr[i];
        }
        for(int i = 0; i < id.length; i++){
            id[i] = i+1;
        }
    }
    static void solve(int[] arr){
        int[] dTime = new int[arr.length/2];
        int[] benefit = new int[arr.length/2];
        int[] id = new int[arr.length/2];
        divideArr(arr, dTime, benefit, id);
        int max = findmax(dTime);
        int[] limit = new int[max];
        mergesort(benefit, dTime, id, 0, benefit.length-1);
        for(int i : benefit){
            System.out.print(i+" ");
        }
        System.out.println("");
        for(int i : dTime){
            System.out.print(i+" ");
        }
        System.out.println("");
        for(int i : id){
            System.out.print(i+" ");
        }
        System.out.println("");
        schedule(dTime, benefit, id, limit, max);


    }
    static void schedule(int[] dTime, int[] benefit, int[] id, int[] limit, int max){
        PriorityQueue <Integer> id_pq = new PriorityQueue<>();
        int count = 0;
        for(int i = 0; i < dTime.length; i++){
            if(limit[dTime[i]-1] < dTime[i]) {
                limit[dTime[i]-1]++;
                while(benefit[i] == benefit[i++])
                id_pq.add(id[i]);
                count++;
            }
            else continue;
            if(count == max) break;
        }
        while(!id_pq.isEmpty()){
            System.out.print(id_pq.poll()+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int[] X = new int[N*2];
            int iter = 0;
            while(str.hasMoreTokens()){
                X[iter++] = Integer.parseInt(str.nextToken());
            }
            solve(X);
        }
    }
}