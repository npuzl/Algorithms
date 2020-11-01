package Homework.homework1;

import java.util.Arrays;
import java.util.Scanner;

public class BestService {
    /**
     * 计算多处最优服务次序的最小平均等待时间
     * 最终时间复杂度为O(nlgn)
     * @param n           任务数目
     * @param s           服务点数目
     * @param serviceTime 各个任务花的时间
     * @return 平均等待时间
     */
    public static int calculateMinWaitTime(int n, int s, int[] serviceTime) {
        //先排序O（nlogn）
        Arrays.sort(serviceTime);
        //waitTime数组存的是每个任务需要等待的时间
        int[] waitTime = new int[n];
        //对于前s个任务的等待时间就是任务的持续时间
        for (int i = 0; i < s; i++) {
            waitTime[i] = serviceTime[i];
        }
        //后面每个任务的等待时间就是这个任务的前面的s个任务中，等待时间最小的任务的等待时间+这个任务的持续时间
        for (int i = s; i < n; i++) {
            int minIndex = arrayMin(waitTime, i - s, i - 1);
            waitTime[i] = waitTime[minIndex] + serviceTime[i];
        }
        //最后求均值
        int sum = 0;
        for (int i : waitTime)
            sum += i;
        return sum / n;
    }

    /**
     * 计算数组中最小元素的索引，区间是双闭区间
     *
     * @param arr   给定元素
     * @param start 数组的起始下标
     * @param end   终止下标
     * @return 最小元素的索引
     */
    private static int arrayMin(int[] arr, int start, int end) {
        int min = start;
        for (int i = start; i <= end; i++) {
            if (arr[i] < arr[min])
                min = i;
        }
        return min;
    }

    public static void main(String[] args) {

        System.out.println(BestService.calculateMinWaitTime(10, 2, new int[]{
                56, 12, 1, 99, 1000, 234, 33, 55, 99, 812
        }));

        int n, s;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        s = scanner.nextInt();
        int[] serviceTime = new int[n];
        for (int i = 0; i < n; i++) {
            serviceTime[i] = scanner.nextInt();
        }
        System.out.println(BestService.calculateMinWaitTime(n, s, serviceTime));


    }
}
