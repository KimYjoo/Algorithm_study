package Algo_12.A;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void solve(int N){
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result= new ArrayList<>();
        
        checkNode(N, 0, tmp, result);
        print(result, N);
    }
    public static void checkNode(int N, int nowloc, ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> result){
        if(nowloc == N){ 
            result.add((ArrayList<Integer>)tmp.stream().collect(Collectors.toList())); 
        }
        for(int i = 0; i < N ; i++){
            if(promising(nowloc,i,tmp)){
                tmp.add(i);
                checkNode(N,nowloc+1,tmp, result);
                tmp.remove(tmp.size()-1);
            }
        }
    }
    public static boolean promising(int row, int i, ArrayList<Integer> tmp){
        boolean ret = true;
        int r = 0;
        while(r < row && ret){
            if(tmp.get(r) == i || (Math.abs(i - tmp.get(r)) == row - r)) ret =false;
            r++;
        }
        return ret;
    }
    public static void print(ArrayList<ArrayList<Integer>> tmp, int N){
        if(tmp.size() == 0)System.out.println("");
        else
            for (int i = 0; i < tmp.size(); i++) {
            
                for (int u = 0; u < N; u++) {
                    for (int j = 0; j < N; j++) {
                        if (tmp.get(i).get(u)== j)
                            System.out.print("Q");
                        else
                            System.out.print("X");
                    }
                    System.out.println();
                }
            }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i = 0; i< T; i++){
            int N = sc.nextInt();
            solve(N);
        }
    }
}
