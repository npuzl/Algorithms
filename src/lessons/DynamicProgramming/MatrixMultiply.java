package lessons.DynamicProgramming;

import java.util.Arrays;

public class MatrixMultiply {
    /**
     * @date 2020/10/07
     * @author zl
     * @param sub 矩阵的下标，有n个矩阵的话这个下标的大小就是n+1
     * @param dp dp[i][j]代表从i乘到j的最少连乘次数，大小应该是(n+1)*(n+1)
     * @param solution 最终在哪断的结果
     */
    public static void matrixMultiply(int []sub,int [][]dp,int [][]solution) {
        int N=sub.length-1;
        //当i=j时，就是同一个矩阵，连乘次数为0
        for (int i = 1; i <= N; i++) {
            dp[i][i]=0;
        }
        for (int t = 2; t <=N; t++){
            for (int i = 1; i <= N-(t-1); i++) {
                //这个双层循环是 用二维数组的右上角
                int j=t+i-1;
                //从对角线右上对角线开始
                //System.out.println(i+" "+j);
                dp[i][j]=dp[i+1][j]+sub[i-1]*sub[i]*sub[j];
                solution[i][j]=i;
                for (int k = i+1; k < j; k++) {
                    int temp=dp[i][k]+dp[k+1][j]+sub[i-1]*sub[k]*sub[j];
                    if(temp <dp[i][j]){
                        dp[i][j]=temp;
                        solution[i][j]=k;
                    }
                }
            }
        }
    }

    public static <T extends Number> void ArrayToString2D(T[][] array){
        for (T[] objects : array) {
            for (T obj:objects) {
                System.out.print(obj + " ");
            }
            System.out.println();
        }
    }

    /**
     * 用于将二维数组输出出来
     * @param dp 需要输出的二维数组
     */
    private static void ArrayToString2D(int[][] dp) {
        for(int[] d:dp){
            for(int o:d){
                System.out.printf("%-6d\t",o);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 输出最终的分割结果
     * @param solution 最终的解决矩阵
     * @param i 从第i开始
     * @param j 到第j结束
     */
    public static void PrintResult(int [][]solution,int i,int j){
        if(i==j) return;
        PrintResult(solution,i,solution[i][j]);
        PrintResult(solution,solution[i][j]+1,j);
        System.out.println("A"+i+"~A"+solution[i][j]+"\tA"+(solution[i][j]+1)+"~A"+j);
    }
    public static void main(String[] args) {
        int []sub=new int[]{30,35,15,5,10,20,25};
        int [][]dp= new int[sub.length][sub.length];
        int [][]solution=new int [sub.length][sub.length];
        matrixMultiply(sub,dp,solution);
        ArrayToString2D(dp);
        ArrayToString2D(solution);
        PrintResult(solution,1,sub.length-1);
    }



}
