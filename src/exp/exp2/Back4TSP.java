package exp.exp2;

import java.util.Arrays;

/**
 * 回溯法求解TSP问题 排列树问题
 *
 * @author zl
 * @version 1.0
 * @date 2020/10/23
 */
public class Back4TSP {

    int NoEdge = -1;
    int bigInt = Integer.MAX_VALUE;
    int[][] matrix; // 邻接矩阵
    int currentCost = 0; // 存储当前代价
    public int bestCost = bigInt;// 当前最优代价
    int[] currentSol; // 当前解
    public int[] bestSol;// 当前最优解
    // 如果需要输出最优路线，就是bestSol[1],bestSol[2].....bestSol[n],bestSol[1]
    int n = 0; // 顶点个数

    private void backtrack(int i) {// i为初始深度
        if (i > n) {
            // 当到底了
            currentCost = currentCost + matrix[currentSol[n]][currentSol[1]];
            // 需要把最后一个城市回去的路程加上
            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSol = currentSol;
                // System.out.println(Arrays.toString(bestSol));
            }
            currentCost -= matrix[currentSol[n]][currentSol[1]];

        } else {
            // 还没到底
            for (int j = i; j <= n; j++) {
                // 第i个位置可能是i，i+1,i+2，....，n
                swap(currentSol[i], currentSol[j]);
                if (check(i)) {
                    // 如果第i个位置可以放
                    currentCost += matrix[currentSol[i - 1]][currentSol[i]];
                    backtrack(i + 1);
                    // 回溯
                    currentCost -= matrix[currentSol[i - 1]][currentSol[i]];
                }
                swap(currentSol[j], currentSol[i]);
            }
        }

    }

    private void swap(int i, int j) {
        int temp = currentSol[i];
        currentSol[i] = currentSol[j];
        currentSol[j] = temp;
    }

    public boolean check(int pos) {
        // 如果第pos-1个位置到第pos个位置有路，则返回true
        return matrix[currentSol[pos - 1]][currentSol[pos]] != NoEdge;
    }

    /**
     * @param b   邻接矩阵
     * @param num 顶点的个数
     */
    public void backtrack4TSP(int[][] b, int num) {
        n = num;
        currentSol = new int[n + 1];
        for (int i = 0; i <= n; i++)
            currentSol[i] = i;
        bestSol = new int[n + 1];
        matrix = b;
        backtrack(2);// 从第二个位置开始找，第一个位置已经定了，不需要找
    }

    public int[] getRoutedSol() {
        int[] route = new int[n + 1];
        if (n >= 0)
            System.arraycopy(bestSol, 1, route, 0, n);
        route[n] = bestSol[1];
        System.out.println(Arrays.toString(route));
        return route;
    }

    public int getShortestLength() {
        return bestCost;
    }

}
