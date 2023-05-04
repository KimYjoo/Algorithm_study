package Algo_5_B;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.HashMap;
public class Main {
    public static int mainAlgo(int[] A, int point){
        int mid = A[point];
        int count = 0;
        for(int i : A){
            if(i == mid) count++;
        }
        if(count > A.length/2) return point;
        else return -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Integer> Hmap = new HashMap<Integer,Integer>();
        int T = sc.nextInt();
        int N = 0;
        StringTokenizer strTmp;
        int[] arr;

        sc.nextLine();
        for(int i = 0; i < T; i++){
            int iter = 0;
            Vector <Integer> V = new Vector<Integer>();
            N = sc.nextInt();
            sc.nextLine();
            arr = new int[N];
            strTmp = new StringTokenizer(sc.nextLine()," ");
            while(strTmp.hasMoreTokens()){
                arr[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            for(int t = 0; t < arr.length; t++){
                if(!V.contains(arr[t])){
                    int tmp = mainAlgo(arr, t);
                    if(tmp >= 0){
                        System.out.println(arr[tmp]);
                        break;
                    }
                    else{
                         V.add(arr[t]);
                    }
                }
            }

        }


    }
}
