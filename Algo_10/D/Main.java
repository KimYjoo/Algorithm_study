package D;

//2019136030 / 김용주/ D번
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
 
public class Main {
    static void bestsum(int[] arr , int M){
        ArrayList<ArrayList<Integer>> table = new ArrayList<>();
        for(int i = 0; i <= M; i++){
            table.add(new ArrayList<>());
        }
        for(int i : arr){
            if(i <= M) table.get(i).add(i);
        }
        for(int i = 1; i < M; i++){
            if(!table.get(i).isEmpty()){
                for(int j : arr){
                    //System.out.println("i : "+i+" j : "+j+" i+j : "+(i+j));
                     
                    if(i+j <= M && (table.get(i+j).size()==0 || table.get(i+j).size() > table.get(i).size()+1)){
                         
                        
                        table.set(i+j, new ArrayList<>(table.get(i)));
                        table.get(i+j).add(j);
                         
                    }
                }
            }
        }
        if(!table.get(M).isEmpty()){
            System.out.print(table.get(M).size() + " ");
            for (int i : table.get(M)) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        else {
            System.out.println(-1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < T; i++){
            int M = sc.nextInt();
            int N = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[N];
            StringTokenizer str = new StringTokenizer(sc.nextLine(), " ");
            int iter = 0;
            while(str.hasMoreTokens()){
                arr[iter++] = Integer.parseInt(str.nextToken());
            }
              
            if(M == 0) System.out.println(0);
            else if(M < Arrays.stream(arr).min().getAsInt()) System.out.println(-1);
            else bestsum(arr, M);
               
        }
        sc.close();
    }
}