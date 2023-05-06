package Algo_8.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
    static class Contents implements Comparable<Contents>{
        int dTime;
        int benefit;
        int id;

        public Contents(int dTime, int benefit, int id){
            this.dTime = dTime;
            this.benefit = benefit;
            this.id = id;
        }

        public int compareTo(Contents a){
            int sor = a.benefit - benefit;
            return sor==0 ? id - a.id : sor;
        }
    }
    static int findmax(Contents[] ct){  //마감시간의 가장 큰수를 찾는다.
        int max = 0;
        for(int i = 0; i < ct.length; i++){
            if(max < ct[i].dTime){
                max = ct[i].dTime;
            }
        }
        return max;
    }
    static int divideArr(int[] mainArr, Contents[] ct, int max){ // 입력된 배열을 각각 배열에 배치시킨다.
        int iter = 0;
        for(int i = 0; i < ct.length; i++){
            int dt = mainArr[iter++];
            max = Math.max(max, dt);
            int bf = mainArr[iter++];
            ct[i] = new Contents(dt, bf, i+1);
        }
        return max;
        
    }
    static void solve(int[] arr){
        
        Contents[] ct = new Contents[arr.length/2];
        int max = -1;   //가장 큰 마감시간을 찾음
        max = divideArr(arr, ct, max); 
        Arrays.sort(ct);
        boolean[] canput = new boolean[max+1];
        ArrayList<Integer> set = new ArrayList<>();
        for(int i = 0; i < ct.length; i++){
            int nowdTime = ct[i].dTime;
            if(!canput[nowdTime]){
                canput[nowdTime] = true;
                set.add(ct[i].id);
            }
            else{
                for(int j = nowdTime-1; j >= 1; j--){
                    if(!canput[j]){
                        canput[j] = true;
                        set.add(ct[i].id);
                        break;
                    }
                }
            }
            if(set.size() == max) break;
            
        }
        
        Collections.sort(set);
        for(int a : set){
            System.out.print(a + " ");
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