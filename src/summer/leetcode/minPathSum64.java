package summer.leetcode;

import java.util.Scanner;

public class minPathSum64 {
    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int m=SC.nextInt(),n = SC.nextInt();
        int[][] nums=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = SC.nextInt();
            }
        }
        System.out.println(minPathSum(nums));

    }
    public static int minPathSum(int[][] grid) {
        if(grid == null||grid.length == 0||grid[0].length == 0) {
            return 0;
        }
        int[][] dp=new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                if(i==0&&j==0){
                    dp[0][0]=grid[0][0];
                }else if(i==0&&j>0){
                    dp[i][j] =dp[i][j-1]+grid[i][j];
                }else if(j==0&&i>0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j]+grid[i][j],dp[i][j-1]+grid[i][j]);
                }

            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

}
