package lessons.DynamicProgramming;

import java.util.Arrays;

/**
 * 用于解决凸多边形的最优三角剖分问题
 */
public class OptimalTriangulationOfConvexPolygon {
    /**
     * 其实这个问题和矩阵连乘问题一样，将矩阵的维度看做顶点值，边看做矩阵，
     *
     * @param n        为顶点数目
     * @param dp       动态规划数组
     * @param solution 结果数组
     */
    public static void DividePolygon(int n, double[][] dp, int[][] solution) {
        //当i=j时，就是同一个矩阵，连乘次数为0
        for (int i = 1; i <= n; i++) {
            dp[i][i]=0;
        }
        for (int t = 2; t <= n; t++){
            for (int i = 1; i <= n -(t-1); i++) {
                int j=t+i-1;
                dp[i][j]=dp[i+1][j]+weight(i-1,i,j,dot);
                solution[i][j]=i;
                for (int k = i+1; k < j; k++) {
                    double temp=dp[i][k]+dp[k+1][j]+weight(i-1,k,j,dot);
                    if(temp <dp[i][j]){
                        dp[i][j]=temp;
                        solution[i][j]=k;
                    }
                }
            }
        }
    }

    /**
     * 生成权重的函数
     * 用的两点间的距离
     * @param i 点
     * @param j 点
     * @param k 点
     * @param dot 全部点
     * @return 返回权重
     */
    public static double weight(int i, int j, int k, double[][] dot) {
        return Math.sqrt(Math.pow((dot[i][0] - dot[j][0]), 2) + Math.pow((dot[i][1] - dot[j][1]), 2)) +
                Math.sqrt(Math.pow((dot[i][0] - dot[k][0]), 2) + Math.pow((dot[i][1] - dot[k][1]), 2)) +
                Math.sqrt(Math.pow((dot[k][0] - dot[j][0]), 2) + Math.pow((dot[j][1] - dot[k][1]), 2));
    }

    /**
     * 测试用的点集，是凸多边形
     */
    public static double [][]dot= new double[][]{
            {3,5},{0,4},{-2,2},{2,-1},{5,-1},{7,1},{8,3},{7,4}};

    public static void main(String[] args) {
        int n=dot.length-1;
        double [][]dp=new double[n+1][n+1];
        int [][]solution=new int[dot.length][dot.length];
        DividePolygon(n,dp,solution);
        for(double[] d:dp)
            System.out.println(Arrays.toString(d));
        System.out.println();
        for(int []s:solution)
            System.out.println(Arrays.toString(s));
    }
}
