package summer.leetcode;

public class MedianOfTwoSortedArrays {
    /**
     * 第一种思路，直接归并，复杂度为O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        int[] merge = new int[m + n];
        while (m != 0 && n != 0) {
            if (nums1[i] < nums2[j]) {
                merge[i + j] = nums1[i];
                i++;
            } else {
                merge[i + j] = nums2[j];
                j++;
            }
            if (i > m - 1 || j > n - 1)
                break;
        }
        //i==m或者j==n
        if (i == m||m==0) {
            for (; j < n; j++) {
                merge[i + j] = nums2[j];
            }
        } else {
            for (; i < m; i++) {
                merge[i + j] = nums1[i];
            }
        }
        if ((m + n) % 2 == 1) {
            return merge[(m + n - 1) / 2];
        } else {
            return (merge[(m + n) / 2] + merge[(m + n - 2) / 2]) / 2.0;
        }
    }

    /**
     * 双指针，都是从头开始，小的往后移位一位
     * 时间复杂度为O(log(m+n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int i=0,j=0;
        int m=nums1.length,n=nums2.length;


    return 0;
    }
    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        double m = medianOfTwoSortedArrays.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(m);

    }
}
