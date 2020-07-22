package summer.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class generateTrees95 {
    private static final Scanner SC = new Scanner(System.in);

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        List<TreeNode> list = generateTrees(SC.nextInt());
        for (TreeNode item : list) {
            levelPrint(item);
        }
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTreesHelper(1, n);
    }

    public static List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTreesHelper(start, i - 1);
            List<TreeNode> rightTrees = generateTreesHelper(i + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currentTree = new TreeNode(i);
                    currentTree.left = left;
                    currentTree.right = right;
                    allTrees.add(currentTree);
                }
            }
        }

        return allTrees;
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

            if (current.val == -1) {
                System.out.print("null ");
            } else {
                System.out.print(current.val);
                System.out.print(" ");
            }


            if (current.val != -1) {
                if (current.left != null) {
                    queue.offer(current.left);
                } else {
                    TreeNode temp = new TreeNode(-1);
                    queue.offer(temp);
                }
            }


            if (current.val != -1) {
                if (current.right != null) {
                    queue.offer(current.right);
                } else {
                    TreeNode temp = new TreeNode(-1);
                    queue.offer(temp);
                }
            }
        }
        System.out.println();
    }
}
