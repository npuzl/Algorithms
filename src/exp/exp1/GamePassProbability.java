package exp.exp1;

import java.math.BigDecimal;

/**
 * 动态规划实验1，解决王者荣耀晋级赛问题
 * @date 2020/10/16
 * @version 1.0
 * @author zl
 *
 */
public class GamePassProbability {
    /**
     * 计算num场比赛最终晋级的概率，假设了要赢得70%的场数
     * @param p 每一场胜率数组
     * @param num 总场数
     * @return 进击的概率
     */
    public double calculatePassProbability(int[] p, int num) {
        double winPercent=0.7;
        int win = (int) Math.ceil(num * winPercent);//需要赢的场数
        double[][] dp = new double[num + 1][num + 1];//dp(i,j)代表一共比赛i场，赢得j场的概率
        for (int i = 0; i <= num; i++) {
            double temp = 1;
            for (int j = 0; j < i; j++) {
                temp *= 0.01 * p[j];
            }
            dp[i][i] = temp;//对角线上的概率为每场比赛都赢，也就是前i场获胜概率乘积
        }

        for (int i = 0; i <= num; i++) {
            double temp = 1;
            for (int j = 0; j < i; j++) {
                temp *= 1 - 0.01 * p[j];
            }
            dp[i][0] = temp;//dp(i,0)为胜0场的概率
        }
        //dp的计算从dp(2,1)开始
        //每一行是从dp(i,1)到dp(i,i-1)
        for (int i = 2; i <= num; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i][j] = dp[i - 1][j] * (1 - 0.01 * p[i - 1]) + 0.01 * p[i - 1] * dp[i-1][j - 1];
                //dp(i,j)=dp(i-1,j)*loss(i)+win(i)*dp(i-1,j-1) 递推关系式
            }
        }
        //最后晋级概率为赢得场数大于等于win
        double ans = 0;
        for (int i = win; i <= num; i++)
            ans += dp[num][i];
        //格式化小数点后五位
        BigDecimal b=new BigDecimal(ans);
        ans= b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        return ans;
    }

}
