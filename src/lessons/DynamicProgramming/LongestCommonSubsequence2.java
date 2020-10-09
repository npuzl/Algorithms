package lessons.DynamicProgramming;

import java.util.Arrays;

/**
 * 书上的代码有点问题，改了一下
 * @date 2020/10/07
 * @author zl
 * @version 1.3
 */
public class LongestCommonSubsequence2 {
    /**
     * @param X   第一个序列
     * @param Y   第二个序列
     * @param res 构造用
     * @return 最长公共子序列长度
     */
    public static int LongestSubsequence(Object[] X, Object[] Y, int[][] res) {
        int m=X.length;
        int n=Y.length;
        int [][]len=new int[m+1][n+1];//用于记录X_i,与Y_j的最长公共子序列的长度
        for (int i = 1; i<=m; i++) {
            len[i][0]=0;
        }
        for (int i = 1; i <= n; i++) {
            len[0][i]=0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(X[i-1]==Y[j-1]){
                    len[i][j]=len[i-1][j-1]+1;
                    res[i][j]=1;//最长公共子序列是和X_(i-1)和Y_(j-1)后面加上一个数组成的
                }else if(len[i-1][j]>=len[i][j-1]){
                    len[i][j] = len[i-1][j];
                    res[i][j]=2;//X_i和Y_j和X_(i-1)月Y_j的最长公共子序列一样
                }else {
                    len[i][j]=len[i][j-1];
                    res[i][j]=3;
                }
            }
        }
        for(int []l:len)
            System.out.println(Arrays.toString(l));
        return len[m][n];

    }

    public static void main(String[] args) {
        Object[] X = new Object[]{'B','D','C','A','B','A'};
        Object[] Y = new Object[]{'A','B','C','B','D','A','B'};
        int[][] res = new int[X.length + 1][Y.length + 1];
        System.out.println("最长公共子序列长度为" + LongestSubsequence(X, Y, res));
        for (int[] r : res)
            System.out.println(Arrays.toString(r));
    }
}
