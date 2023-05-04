package Algo_6_B;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static int find2ndMx(int arrMain[]){
        /*int newN = arrMain.length + arrMain.length-1;
        int[] arrTmp = new int[newN];
        for(int i = 0; i < arrMain.length; i++){
            arrTmp[i + arrMain.length-1] = arrMain[i];
        }
        //새로만든 배열에 기존배열을 뒤로 밀착하여 넣는다.
        for(int i : arrTmp) System.out.print(i+" ");
        System.out.println("");*/
        int arrSize = arrMain.length;
        int newSize = arrSize*2 - 1;

        int[] arrNew = new int[newSize];
        System.arraycopy(arrMain, 0, arrNew, arrSize-1, arrSize);
        //새로만든 배열에 기존배열을 우로 밀착
        
        for(int i = arrSize-2, j = newSize-1; i >= 0; i--, j -= 2){  
            //토너먼트 진행
            arrNew[i] = arrNew[j] >= arrNew[j-1] ? arrNew[j] : arrNew[j-1];
        }

        int left = 1;
        int max = arrNew[0];
        int ret = -987654321;

        while(left < newSize-1){
            int curr = 0; // 현재 라운드에서 최대값과 대결한 값
            int maxLoc = 0; // 현재 라운드의 최대값의 위치
            if(arrNew[left] == max){
                maxLoc = left;
                curr = arrNew[left+1];
            }
            else{
                maxLoc = left + 1;
                curr = arrNew[left];
            }
            //현재 라운드의 최대값의 위치와 그 상대의 값을 얻는다.
            
            if(curr == max){
                //현재라운드의 최대값의 상대가 최대값이랑 같은 수라면 해당값이 두 번째로 큰값이 된다.
                return curr;
            }

            if(ret < curr) ret = curr;
            //현재라운드에서 최댓값과 대결한 값이 더 크다면 갱신한다.

            left = 2 * maxLoc + 1;
            //현재 최대값이 위치한 노드에서 왼쪽 자식노드의 위치를 불러온다.
        }
        return ret;
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
            strTmp = new StringTokenizer(sc.nextLine(), " ");
            int iter = 0;
            X = new int[N];
            while(strTmp.hasMoreTokens()){
                X[iter++] = Integer.parseInt(strTmp.nextToken());
            }
            System.out.println(find2ndMx(X));
        }
    }
}
