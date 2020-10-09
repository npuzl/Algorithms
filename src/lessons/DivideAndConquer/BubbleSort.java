package lessons.DivideAndConquer;


import java.util.Arrays;
/**时间复杂度为O(n^2)
 * @version 1.0
 * @date 2020/09/17
 * @author zl
 */
public class BubbleSort {
    public static void bubbleSort(int[] list) {
        int length = list.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{9, 5, 8, 1, 3, 6, 74, 6, 4, 2, 8};
        bubbleSort(list);
        System.out.println(Arrays.toString(list));
    }
}
