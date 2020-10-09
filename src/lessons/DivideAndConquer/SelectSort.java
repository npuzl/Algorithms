package lessons.DivideAndConquer;

import java.util.Arrays;

/**时间复杂度为O(n^2)
 * @version 1.0
 * @date 2020/09/17
 * @author zl
 */


public class SelectSort {

    public static void selectSort(int[] list){
        for (int i = 0; i < list.length; i++) {
            for (int j = i; j < list.length; j++) {
                if(list[j] < list[i]){
                    int temp=list[j];
                    list[j]=list[i];
                    list[i]=temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] list = new int[]{9, 5, 6, 7, 8, 2, 3, 6, 88, -1, 3};
        selectSort(list);
        System.out.println(Arrays.toString(list));
    }
}
