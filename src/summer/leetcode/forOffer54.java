package summer.leetcode;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class forOffer54 {
    private static final Scanner SC = new Scanner(System.in);
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    public static TreeNode toTree(String input) {
        if (input.length() <= 0) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(input, ",", false);
        int size = tokenizer.countTokens();
        LinkedList<String> nodes = new LinkedList<>();
        while (tokenizer.hasMoreTokens()) {
            nodes.add(tokenizer.nextToken());
        }
        TreeNode[] nodeArray = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            if (!"null".equals(nodes.get(i))) {
                if (nodeArray[i] == null) {
                    nodeArray[i] = new TreeNode(Integer.parseInt(nodes.get(i)));
                }
                int idx = 2 * i + 1;
                if (idx <= size - 1 && !"null".equals(nodes.get(idx))) {
                    nodeArray[idx] = new TreeNode(Integer.parseInt(nodes.get(idx)));
                    nodeArray[i].left = nodeArray[idx];
                } else {
                    nodeArray[i].left = null;
                }
                idx++;
                if (idx <= size - 1 && !"null".equals(nodes.get(idx))) {
                    nodeArray[idx] = new TreeNode(Integer.parseInt(nodes.get(idx)));
                    nodeArray[i].right = nodeArray[idx];
                } else {
                    nodeArray[i].right = null;
                }

            }
        }

        return nodeArray[0];
    }
    
    public static void main(String[] args) {
        TreeNode treeNode=toTree(SC.nextLine());
        System.out.println(kthLargest(treeNode,SC.nextInt()));
    }
    public static int k,res;
    public static int kthLargest(TreeNode root, int kk){
        k =kk;
        kthLargestHelper(root);
        return res;
    }
    public static void kthLargestHelper(TreeNode root){
        if (root == null) {
            return;
        }
        kthLargestHelper(root.right);
        if(k==0) {
            return;
        }
        if(--k==0) {
            res=root.val;
        }
        kthLargestHelper(root.left);
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
