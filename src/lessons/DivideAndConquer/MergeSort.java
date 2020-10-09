package lessons.DivideAndConquer;

import java.util.Arrays;

/**
 * 归并排序的时间复杂度为O(nlogn)
 * 最佳时间复杂度为O(n)
 * 最差时间复杂度为O(nlogn)
 * 20200916  还是不太明白
 * @author zl
 * @version 1.0
 * @date 2020/09/15
 */
public class MergeSort {
    /**
     * 迭代法实现
     */
    public static void mergeSort(int[] list) {
        int listLen = list.length;
        int[] res = new int[listLen];
        int block, start;


        for (block = 1; block < listLen * 2; block = 2 * block) {
            for (start = 0; start < listLen; start += 2 * block) {
                int low = start;
                int mid = Math.min((start + block), listLen);
                int high = Math.min((start + 2 * block), listLen);
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = high;
                while (start1 < end1 && start2 < end2) {
                    res[low++] = list[start1] < list[start2] ? list[start1++] : list[start2++];
                }
                while (start1 < end1) {
                    res[low++] = list[start1++];
                }
                while (start2 < end2) {
                    res[low++] = list[start2++];
                }
            }
            int[] temp = list;
            list = res;
            res = temp;
        }
        res = list;
    }


    /**
     * 递归实现
     *
     * @param list 待排序数组
     */
    public static void mergeSort2(int[] list) {
        int[] temp = new int[list.length];
        sort(list, 0, list.length - 1, temp);
    }

    /**
     * @param list  待排序数组
     * @param start 排序数组起始位置
     * @param end   排序数组终止位置
     * @param temp  临时空间
     */

    public static void sort(int[] list, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(list, start, mid, temp);
            sort(list, mid + 1, end, temp);
            merge(list, start, mid, end, temp);
        }
    }

    /**
     * 主要递归函数
     *
     * @param list  主要数组
     * @param start 起始值索引
     * @param mid   中间值索引
     * @param end   末尾索引
     * @param temp  临时数组
     */
    public static void merge(int[] list, int start, int mid, int end, int[] temp) {
        int i = start;//左序列
        int j = mid + 1;//右序列
        int t = 0;
        while (i <= mid && j <= end) {
            if (list[i] <= list[j]) {
                temp[t++] = list[i++];
            } else {
                temp[t++] = list[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = list[i++];
        }
        while (j <= end) {
            temp[t++] = list[j++];
        }
        t = 0;
        while (start <= end) {
            list[start++] = temp[t++];
        }

    }

    public static void main(String[] args) {
        int[] list = new int[]{9, 5, 6, 7, 8, 2, 3, 6, 88, -1, 3};
        mergeSort2(list);
        System.out.println(Arrays.toString(list));
    }
}
