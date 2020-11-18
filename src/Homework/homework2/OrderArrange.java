package Homework.homework2;

/**
 * 这道题也是一个排序树，这道题的难点在于check函数的设计
 *
 * @author zl
 * @version 1.0
 * @date 2020/11/08
 */
public class OrderArrange {
    int n;
    int[][] timeList;
    int[] currentSol;
    int[] bestSol;
    int longestTime = 0;
    int currentTime = 0;
    int timeMAX = 1;
    int[] timeOccu;

    /**
     * @param n
     * @param timeList 占用时间列表应该是左闭右开，比如说1 2代表占用时间1，就一格
     */
    OrderArrange(int n, int[][] timeList) {
        this.n = n;
        this.timeList = timeList;
        currentSol = new int[n];
        for (int i = 0; i < n; i++) {
            currentSol[i] = i;
        }
        for (int i = 0; i < n; i++) {
            if (timeList[i][1] > timeMAX)
                timeMAX = timeList[i][1];
        }
        timeOccu = new int[timeMAX + 1];
        backtrace(0);
    }

    private void backtrace(int i) {
        if (i >= n) {
        } else {
            for (int j = i; j < n; j++) {
                swap(i, j);
                //这里要注意行索引是currentSol[i]，第一次写的时候没注意
                currentTime += timeList[currentSol[i]][1] - timeList[currentSol[i]][0];
                for (int k = timeList[currentSol[i]][0]; k < timeList[currentSol[i]][1]; k++) {
                    timeOccu[k] += 1;
                }
                if (check(i)) {
                    if (currentTime > longestTime) {
                        longestTime = currentTime;
                        bestSol = currentSol.clone();
                    }
                    backtrace(i + 1);
                }
                for (int k = timeList[currentSol[i]][0]; k < timeList[currentSol[i]][1]; k++) {
                    timeOccu[k] -= 1;
                }
                currentTime -= timeList[currentSol[i]][1] - timeList[currentSol[i]][0];
                swap(i, j);
            }
        }
    }

    private boolean check(int i) {
        //只要检查有没有2就好了
        for (int k = timeList[currentSol[i]][0]; k < timeList[currentSol[i]][1]; k++) {
            if (timeOccu[k] == 2) {
                return false;
            }
        }
        return true;
    }

    private void swap(int i, int j) {
        int temp = currentSol[i];
        currentSol[i] = currentSol[j];
        currentSol[j] = temp;
    }

    public int getLongestTime() {
        return longestTime;
    }

    public static void testCase() {
        int n = 4;
        int[][] list = new int[][]{
                new int[]{1, 2}, new int[]{3, 5}, new int[]{1, 4}, new int[]{4, 5}
        };
        System.out.println(new OrderArrange(n, list).getLongestTime());
    }

    public static void main(String[] args) {
        OrderArrange.testCase();
    }
}
