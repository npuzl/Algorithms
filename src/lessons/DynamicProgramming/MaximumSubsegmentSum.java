package lessons.DynamicProgramming;

/**
 * 最大子段和问题的实现
 *
 * @author zl
 * @date 2020/10/08
 */
public class MaximumSubsegmentSum {
    /**
     * 动态规划法实现最大子段和算法
     * @param seq 输入的序列
     * @param node 第一个元素为最大子段和的起始位置，第二个元素为终止位置
     * @return 最大子段和
     */
    public static int MaxSubSumDP(int[] seq, int[] node) {
        if (seq == null) return 0;
        int sum = 0,temp=0;
        for (int i = 0; i < seq.length; i++) {
            //如果大于等于0，则继续往后面加，否则重置
            if (temp >= 0) {
                temp += seq[i];
            } else {
                temp = seq[i];
                //保存起始位置
                node[0] = i + 1;
            }
            //当前最大值保存一下下标和最大值
            if (temp > sum) {
                sum = temp;
                node[1] = i + 1;
            }
        }

        return sum;

    }
    //TODO 有空再写
    public static int MaxSubSumDC(int[] seq, int[] node) {
        node[0] = 1;
        node[1] = 2;
        return 0;

    }

    public static void main(String[] args) {
        int[] seq = new int[]{5, -6, 1, -2, 3, 4, -5, 9, 1, 8};
        int start = 0, end = 0;
        int[] node = new int[]{start, end};
        System.out.println("动态规划法：最大子段和为：" + MaxSubSumDP(seq, node) + "，从第" + node[0] + "个数到第" + node[1] + "个数");
        System.out.println("分治法：最大子段和为：" + MaxSubSumDC(seq, node) + "，从第" + node[0] + "个数到第" + node[1] + "个数");
    }
}
