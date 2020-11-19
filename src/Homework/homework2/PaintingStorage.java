package Homework.homework2;

import java.util.Arrays;

/**
 * 本作业在github上管理，做题记录都在github上，
 * github地址：https://github.com/npuzl/Algorithms
 * 老师如果打开链接了的话麻烦点个star呗
 *
 * 最简单的方法，就是建立一棵深度为m*n的子集树，每个节点只有0，1两种选择，这样复杂度感觉挺大的
 * 这是最简单也最蠢的方法，这个复杂度是O(2^(m+n))，而且在check部分，我check了每一个点处的值
 * 但是这个可以求出来
 * @author zl
 * @version 2.0
 * @date 2020/11/10
 */
public class PaintingStorage {
    int m, n;
    int bestCost;
    int currentCost = 0;
    int[][] currentSol;
    int[][] bestSol;
    int[][] space;

    PaintingStorage(int m, int n) {
        this.m = m;
        this.n = n;
        bestCost = m * n;
        currentSol = new int[m][n];
        bestSol = new int[m][n];
        space = new int[m][n];
        backtrace(0, 0);
    }

    private void backtrace(int i, int j) {
        if (i == m || j == n) {

            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    //只要有还没覆盖上的，结束
                    if (space[x][y] == 0)
                        return;
                }
            }
            /*
            for(int []c:currentSol)
                System.out.println(Arrays.toString(c));
            System.out.println("-----------------------------");
            */

            if (currentCost < bestCost) {
                bestCost = currentCost;
                //二维数组要实现深拷贝 需要一行一行clone
                for (int l = 0; l < currentSol.length; l++)
                    bestSol[l] = currentSol[l].clone();
            }
        } else {
            for (int k = 0; k <= 1; k++) {
                currentSol[i][j] = k;
                currentCost += k;
                update(k, i, j);
                if (check(i, j)) {
                    if (j == n - 1)
                        backtrace(i + 1, 0);
                    else backtrace(i, j + 1);
                }
                currentCost -= k;
                restore(k, i, j);
            }
        }
    }

    private void update(int k, int i, int j) {
        if (k == 1) {
            space[i][j] += 1;
            if (i - 1 >= 0)
                space[i - 1][j] += 1;
            if (i + 1 < m)
                space[i + 1][j] += 1;
            if (j - 1 >= 0)
                space[i][j - 1] += 1;
            if (j + 1 < n)
                space[i][j + 1] += 1;
        }
    }

    private void restore(int k, int i, int j) {
        if (k == 1) {
            space[i][j] -= 1;
            if (i - 1 >= 0)
                space[i - 1][j] -= 1;
            if (i + 1 < m)
                space[i + 1][j] -= 1;
            if (j - 1 >= 0)
                space[i][j - 1] -= 1;
            if (j + 1 < n)
                space[i][j + 1] -= 1;
        }
    }

    private boolean check(int i, int j) {
        //只要检测到(i,j+1)就好了
        /*
        for (int x = 0; x < i - 1; x++) {
            for (int y = 0; y < n; y++) {
                if (space[x][y] == 2) {
                    return false;
                }
            }
        }
        for (int y = 0; y <= j; y++) {
            if (space[i][y] == 2)
                return false;
        }
        if(j+1<n&&space[i][j+1]==2)
            return false;*/
        for (int x = 0; x < m; x++)
            for (int y = 0; y < n; y++)
                if (space[x][y] == 2)
                    return false;
        return true;
    }

    public int getBest() {
        return bestCost;
    }

    public int[][] getArrange() {
        return bestSol;
    }

    public static void testcase() {
        int m = 4, n = 4;
        PaintingStorage paintingStorage = new PaintingStorage(m, n);
        System.out.println(paintingStorage.getBest());
        System.out.println(Arrays.deepToString(paintingStorage.getArrange()));
    }

    public static void main(String[] args) {
        PaintingStorage.testcase();
    }
}
