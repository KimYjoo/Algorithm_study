package Algo_8.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static class Values implements Comparable<Values>{
        int key;
        int len;
        String begin;
        public Values(String value){
            this.begin = value;
            this.key = value.charAt(0)-'0';
            this.len = value.length();
        }
        public int compareTo(Values a){
            int comp = (a.begin+begin).compareTo(begin + a.begin);
            
            return comp;
        }
    }
    static void settingValues(Values[] v, String[] arr){
        for(int i = 0; i < v.length; i++){
            v[i] = new Values(arr[i]);
        }
    }
    static void solve(String[] arr){
        Values[] values = new Values[arr.length];
        settingValues(values, arr);
        Arrays.sort(values);
        for(int i = 0; i < values.length; i++){
            System.out.print(values[i].begin);
        }
        System.out.println("");



    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i = 0; i < T; i++){
            int N = sc.nextInt();
            sc.nextLine();
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            String[] X = new String[N];
            int iter = 0;
            boolean swt = true;
            while(str.hasMoreTokens()){
                X[iter] = str.nextToken();
                if(swt && !X[iter].equals( "0")){
                    swt = false;
                }
                iter++;
            }
            if(N == 1) System.out.println(X[0]);
            else if(swt) System.out.println("0");
            else solve(X);
        }
    }
}