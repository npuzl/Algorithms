package summer.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @param <T>
 */
public class CountOfSmallerNumbersAfterSelf<T extends Comparable> {

    public List<Integer> countSmaller(T[] nums) {
        int[] counts = new int[nums.length];
        int n = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                if (nums[j].compareTo(nums[i]) < 0) {
                    counts[i] = counts[j] + 1;
                    break;
                }

            }
        }

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int c : counts)
            arr.add(c);
        return arr;
    }


    public static void main(String[] args) {
        Integer[] nums = new Integer[]{5, 2, 6, 1};
        System.out.println(new CountOfSmallerNumbersAfterSelf<Integer>().countSmaller(nums));
    }
}
