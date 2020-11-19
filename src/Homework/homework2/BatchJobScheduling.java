package Homework.homework2;

import java.util.Arrays;

/**
 * 本作业在github上管理，做题记录都在github上，
 * github地址：https://github.com/npuzl/Algorithms
 * 老师如果打开链接了的话麻烦点个star呗
 *
 * 这是一个排列树
 * 这道题的问题不在于check上，而在于更新当前时间上，要注意更新的是个啥
 */
public class BatchJobScheduling {
    int n;
    double[][] timeList;
    int[] currentSol;
    int[] bestSol;
    double currentCost = 0;
    double bestCost;

    BatchJobScheduling(int n, double[][] timeList) {
        this.n = n;
        this.timeList = timeList;
        currentSol = new int[n];
        for (int i = 0; i < n; i++) {
            currentSol[i] = i + 1;
        }
        bestSol = new int[n];
        currentCost = 0;
        bestCost = Double.MAX_VALUE;
        backtrace(0);
    }

    private void backtrace(int i) {
        if (i >= n) {
            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSol = currentSol.clone();
            }
        } else {
            for (int j = i; j < n; j++) {
                swap(i, j);
                //计算currentCost要比较timeList[i-1][1]和timeList[i][0]哪个大，取较大值加上timeList[i][1]
                if (i == 0) {
                    currentCost += timeList[currentSol[0]-1][0] + timeList[currentSol[0]-1][1];
                } else {
                    currentCost += Math.max(timeList[currentSol[i - 1] - 1][1], timeList[currentSol[i] - 1][0])
                            - Math.min(timeList[currentSol[i - 1] - 1][1], timeList[currentSol[i] - 1][0])
                            + timeList[currentSol[i] - 1][1];
                }

                //update currentCost
                backtrace(i + 1);
                if (i == 0) {
                    currentCost -= timeList[currentSol[0]-1][0] + timeList[currentSol[0]-1][1];
                } else {
                    currentCost -= Math.max(timeList[currentSol[i - 1] - 1][1], timeList[currentSol[i] - 1][0])
                            - Math.min(timeList[currentSol[i - 1] - 1][1], timeList[currentSol[i] - 1][0])
                            + timeList[currentSol[i] - 1][1];
                }
                swap(i, j);
            }
        }
    }

    private void swap(int i, int j) {
        int temp = currentSol[i];
        currentSol[i] = currentSol[j];
        currentSol[j] = temp;
    }

    private int[] getBest() {
        return bestSol;
    }

    private double getBestCost() {
        return bestCost;
    }

    public static void testcase() {
        int n = 3;
        double[][] list = new double[][]{
                new double[]{2, 1}, new double[]{3, 1}, new double[]{2, 3}
        };
        BatchJobScheduling batchJobScheduling = new BatchJobScheduling(n, list);
        System.out.println(Arrays.toString(batchJobScheduling.getBest()));
        System.out.println(batchJobScheduling.getBestCost());
    }

    public static void main(String[] args) {
        BatchJobScheduling.testcase();
    }
}
