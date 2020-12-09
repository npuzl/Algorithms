package lessons.DivideAndConquer;

import java.util.Random;

/**
 * 选择第k大(小)的元素
 * 时间复杂度O(n)
 *
 * @author zl
 * @version final version
 * @date 2020/11/16
 */
public class kthSelect<T extends Comparable> {

    kthSelect(T[] num, int k) {
        T[] list = num.clone();
        System.out.println(kthSelectHelper(list, k, 0, num.length - 1));


    }

    /**
     * @param list
     * @param k     第K大
     * @param left  左区间
     * @param right 右区间 都是闭区间
     * @return
     */
    public T kthSelectHelper(T[] list, int k, int left, int right) {
        if (k > list.length || k < 1) return null;
        int index = kthSelectFromList(list, k, left, right);
        if (index == k - 1) {
            return list[index];
        }
        if (index < k - 1)
            return kthSelectHelper(list, k, index + 1, right);
        else
            return kthSelectHelper(list, k, left, index - 1);
    }

    /**
     * 这个函数用于在left和right随机找一个，返回其在整个数组中的索引
     * 这里是左边大右边小
     *
     * @param list
     * @param k
     * @param l
     * @param r
     * @return
     */
    public int kthSelectFromList(T[] list, int k, int l, int r) {
        int left = l;
        int right = r;
        int pos = new Random().nextInt(r - l + 1) + l;
        T pivot = list[pos];//以第pos+1个数为枢轴
        int pit = pos;//坑的索引
        while (left < right) {
            while (list[left].compareTo(pivot) > 0 && left < right) {
                left++;
            }
            if (left <= right) {
                list[pit] = list[left];
                pit = left;
            }
            while (list[right].compareTo(pivot) < 0 && left < right) {
                right--;
            }
            if (left <= right) {
                list[pit] = list[right];
                pit = right;
            }
        }
        list[pit] = pivot;
        return pit;
    }


    public static void main(String[] args) {
        Integer[] list = new Integer[]{3, 8, 5, 6, 7, 4, 9, 2, 11, 0};
        new kthSelect<Integer>(list, 1);
    }
}
