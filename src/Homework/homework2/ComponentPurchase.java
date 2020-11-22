package Homework.homework2;

import java.util.Arrays;

/**
 * 本作业在github上管理，做题记录都在github上，
 * github地址：https://github.com/npuzl/Algorithms
 * 老师如果打开链接了的话麻烦点个star呗
 * 如果有错误，可以提Issues，谢谢！
 * 这道题是排列树，每个节点有m个选择
 * 如果没有 “每个厂商处只能购买一个”，这道题就是子集树
 *
 * @author zl
 * @version 2.0
 * @date 2020/11/01
 */
public class ComponentPurchase {
    public int[] out = null;
    public int minW = Integer.MAX_VALUE;
    public int currentCost = 0;
    public int[] currentSol;
    public int currentW = 0;
    /**
     * 机器的部件数目
     */
    int n;
    /**
     * m个不同的供应商
     */
    int m;
    /**
     * 总预算
     */
    int cost;
    /**
     * w[i][j]为从j处购得零件i的重量
     */
    int[][] w;
    /**
     * c[i][j]为从j处购得零件i的价格
     */
    int[][] c;

    ComponentPurchase(int n, int m, int cost, int[][] w, int[][] c) {
        this.n = n;
        this.m = m;
        this.cost = cost;
        this.w = w;
        this.c = c;
        currentSol = new int[n];
        for (int i = 1; i <= n; i++) {
            currentSol[i - 1] = i;
        }
        out = new int[n];
        if (m < n) {
            System.out.println("供应商的数目少于零件数目");
            return;
        }
        calculate(0);
    }

    public void calculate(int i) {

        if (i >= n) {
            if (currentW < minW) {
                minW = currentW;
                out = currentSol.clone();
            }
        } else {
            for (int j = i; j < m; j++) {
                swap(i, j);
                //先把cost和W加上，列是厂商，i是零件
                currentCost += c[i][currentSol[j] - 1];
                currentW += w[i][currentSol[j] - 1];
                if (currentCost <= cost)
                    calculate(i + 1);
                currentCost -= c[i][currentSol[j] - 1];
                currentW -= w[i][currentSol[j] - 1];
                swap(i, j);
            }
        }
    }

    private void swap(int i, int j) {
        int temp = currentSol[i];
        currentSol[i] = currentSol[j];
        currentSol[j] = temp;
    }

    public static void testCase() {
        int n = 3, m = 3, cost = 7;
        int[][] w = new int[][]{new int[]{1, 2, 3}, new int[]{3, 2, 1}, new int[]{2, 3, 2}};
        int[][] c = new int[][]{new int[]{1, 2, 3}, new int[]{5, 4, 2}, new int[]{2, 1, 2}};
        ComponentPurchase componentPurchase = new ComponentPurchase(n, m, cost, w, c);
        System.out.println(Arrays.toString(componentPurchase.out));
        System.out.println(componentPurchase.minW);
        assert Arrays.equals(componentPurchase.out, new int[]{1, 2, 3});
        assert componentPurchase.minW == 5;
    }

    public static void main(String[] args) {
        ComponentPurchase.testCase();
    }
}
