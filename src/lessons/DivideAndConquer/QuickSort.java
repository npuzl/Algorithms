package lessons.DivideAndConquer;

import java.util.Arrays;
/**
 * 快速排序
 * 时间复杂度O(nlogn)
 * @author zl
 * @date 2020/09/17
 * @version 1.0
 */
public class QuickSort {
    public static int adjustList(int[] list, int left, int right){
        int i=left,j=right;
        int x=list[left];
        while (i < j) {

            while (i < j&&list[j]>=x) {
                j--;
            }
            if (i < j) {
                list[i]=list[j];
                i++;
            }

            while (i < j&&list[i]<x) {
                i++;
            }
            if (i < j) {
                list[j]=list[i];
                j--;
            }

        }
        list[i]=x;
        return i;
    }
    public static void quickSort(int[] list,int left,int right){
        if(left<right){
            //基准位置
            int i=adjustList(list,left,right);
            quickSort(list,left,i-1);
            quickSort(list,i+1,right);
        }

    }
    public static void main(String[] args) {
        int[] list = new int[]{9, 5, 6, 7, 8, 2, 3, 6, 88, -1, 3};
        quickSort(list,0,list.length-1);
        System.out.println(Arrays.toString(list));
    }

}
