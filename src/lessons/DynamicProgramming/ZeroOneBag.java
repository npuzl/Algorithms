package lessons.DynamicProgramming;

import java.util.Arrays;
import java.util.Random;

/**
 * 0-1背包问题
 *
 * @author zl
 * @date 2020/10/08
 */
public class ZeroOneBag {
    /**
     * @param n      总物品数
     * @param weight 每个物品的重量
     * @param value  每个物品的价值
     * @param cap    包的总容量
     * @param decide 决策矩阵
     * @return 最大总价值
     */
    //TODO decide部分有问题
    public static int zeroOneBag(int n, int[] weight, int[] value, int cap, int[] decide) {
        //建立 cap*n的矩阵dp,dp(i,j)代表容量为i的包里装j,j+1,j+2...物品，最大价值
        int[][] dp = new int[cap + 1][n + 1];

        for (int i = 0; i <= cap; i++) {
            if (i > weight[n - 1])
                dp[i][n - 1] = value[n - 1];

            else
                dp[i][n - 1] = 0;
        }
        /*    0 1 2 3 ...... n-1 ! n  n+1
         * ------------------------
         * 0|                0
         * 1|
         * 2|
         * 3|
         * .|
         * .|
         * .|
         * .|
         *  |
         *  |
         *  |                v
       cap-1|                v
         cap|                v
         */

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= cap; j++) {
                //如果有j的空间不足以装下第i个物品,那就和j个空间装第i+1个物品一样
                if (j < weight[i]) {
                    dp[j][i] = dp[j][i+1];
                } else {
                    //当够放时，就要比较放进去和不放进去哪个大
                    dp[j][i] = Math.max(dp[j][i+1],dp[j-weight[i]][i+1]+value[i]);
                    //如果放，则记录
                    if(dp[j][i+1]<dp[j-weight[i]][i+1]+value[i]){
                        decide[i]=1;
                    }
                }
            }
        }
        //只要返回dp[0][:]中的最大值就行
        /*
        for(int []d:dp)
            System.out.println(Arrays.toString(d));*/
        return dp[cap][0];
    }

    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();
        int cap = 100;
        int[] weight = new int[n];
        int[] value = new int[n];
        int[] decide = new int[n];

        for (int i = 0; i < n; i++) {
            weight[i] = random.nextInt((int) (cap / Math.sqrt(n)));
            value[i] = random.nextInt(100);
        }

        System.out.println("Weight:" + Arrays.toString(weight));
        System.out.println("Value:" + Arrays.toString(value));
        System.out.println("Max value sum："+zeroOneBag(n, weight, value, cap, decide));
        //System.out.println(Arrays.toString(decide));
/*
        int[] weight2 = new int[]{100,1,1,1,1,1,1,1,1,1};
        int[] value2 = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] decide2 = new int[n];
        System.out.println("weight:" + Arrays.toString(weight2));
        System.out.println("value:" + Arrays.toString(value2));
        System.out.println(zeroOneBag(n, weight2, value2, cap, decide2));
        System.out.println(Arrays.toString(decide2));*/
    }
}
