package summer.leetcode;

import java.util.*;

public class interviewer0402 {
    private static final Scanner SC = new Scanner(System.in);

    /*
     * sc.next();//不接受空格，在接收到有效数据前，所有的空格或者tab键等输入被忽略，若有有效数据，则遇到这些键退出
     * sc.nextLine();//nextLine()可以接收空格或者tab键，其输入应该以enter键结束。 sc.nextInt();
     * sc.nextFloat();
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int size = 0;
        size = SC.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = SC.nextInt();
        }
        TreeNode treeNode = sortedArrayToBST(nums);
        levelPrint(treeNode);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        TreeNode n = new TreeNode(nums[nums.length / 2]);
        n.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, nums.length / 2));
        n.right = sortedArrayToBST(Arrays.copyOfRange(nums, nums.length / 2 + 1, nums.length));
        return n;
    }

    public static void levelPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode current;
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            System.out.println(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

    }

}
