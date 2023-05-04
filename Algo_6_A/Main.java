package Algo_6_A;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Main{
    public static void findMxMn(int[] arrMain){
        int max = arrMain[0], min = arrMain[1];
        if(max < min) {
            int tmp = max;
            max = min;
            min = tmp;
        }
        for(int i = 2; i < arrMain.length-1; i += 2){
            if(arrMain[i] > arrMain[i+1]){
                //i 가 W , i+1이 L일때
                if(arrMain[i] > max) max = arrMain[i];
                //기존 W인 값인 max와 현재 W인 값을 비교해서 W끼리 비교 후 갱신
                if(arrMain[i+1] < min) min = arrMain[i+1];
                //기존 L인 값인 min와 현재 L인 값을 비교해서 L끼리 비교 후 갱신
            }
            else{
                //i 가 L , i+1이 W일때
                if(arrMain[i+1] > max) max = arrMain[i+1];
                if(arrMain[i] < min) min = arrMain[i];
            }
        }
        if(arrMain.length%2 != 0){
            if(max < arrMain[arrMain.length-1]) max = arrMain[arrMain.length-1];
            else if(min > arrMain[arrMain.length-1]) min = arrMain[arrMain.length-1];
        }
        System.out.println(max +" "+ min);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        int N;
        int[] X;
        StringTokenizer strTmp;
        for(int i = 0; i < T; i++){
            N = sc.nextInt();
            sc.nextLine();
            X = new int[N];
            int iter = 0;
            strTmp = new StringTokenizer(sc.nextLine(), " ");
            while(strTmp.hasMoreTokens()){
                X[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            if(N == 1) System.out.println(X[0] + " " + X[0]);
            else findMxMn(X);
        }
    }
}