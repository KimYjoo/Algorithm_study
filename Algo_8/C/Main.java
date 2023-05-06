package Algo_8.C;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static void solve(int[] arr,boolean[] loca){
        boolean[] isDstd = new boolean[arr.length];
        for(int i = 0; i < arr.length; i++){
            if(isDstd[i]) continue;
            int direction = loca[i] ? 1 : -1;
            int nowPlanet = i;
            for(int j = i + direction; j>=0 && j <arr.length; j += direction){
                if(isDstd[j]) continue;
                if(loca[nowPlanet] == loca[j]) break;

                if(arr[nowPlanet] > arr[j]){
                    isDstd[j] = true;
                }
                else if(arr[nowPlanet] < arr[j]){
                    isDstd[nowPlanet] = true;
                    break;
                }
                else {
                    isDstd[j] = true;
                    isDstd[nowPlanet] = true;
                    break;
                }
            }
        }
        for(int i = 0; i < arr.length; i++){
            if(!isDstd[i]){
                if(!loca[i]) arr[i] *= -1;
                System.out.print(arr[i]+" ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int[] x = new int[N];
            boolean[] loca = new boolean[N];
            int iter = 0;
            while (str.hasMoreTokens()) {
                int temp = Integer.parseInt(str.nextToken());
                if(temp > 0) loca[iter] = true;
                else temp = Math.abs(temp);
                x[iter++] = temp;
            }
            solve(x, loca);
        }
    }
}
