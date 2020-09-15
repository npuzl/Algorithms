package lessons.DivideAndConquer;

import java.util.Arrays;

/**
 * @version 1.0
 * @date 2020/09/15
 * @author zl
 * 归并排序的时间复杂度为O(nlogn)
 * 最佳时间复杂度为O(n)
 * 最差时间复杂度为O(nlogn)
 */
public class MergeSort {
    /**
     * 迭代法实现
     *
     *
     */
    public static void mergeSort(int[] list){
        //TODO

    }

    /**
     * 递归实现
     * @param list 待排序数组
     */
    public static void mergeSort2(int[] list){
        int []temp = new int[list.length];
        sort(list,0,list.length-1,temp);
    }

    /**
     *
     * @param list 待排序数组
     * @param start 排序数组起始位置
     * @param end 排序数组终止位置
     * @param temp 临时空间
     */

    public static void sort(int[] list, int start,int end,int[] temp){

    }
    public static void main(String[] args) {
        int[] list=new int[]{9,5,6,7,8,2,3,6,88,-1,3};
        mergeSort2(list);
        System.out.println(Arrays.toString(list));
    }
}
