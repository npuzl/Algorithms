package summer.leetcode;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class interview68II {

    private static final Scanner SC = new Scanner(System.in);

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
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
        TreeNode root=toTree(SC.nextLine());
        TreeNode p=root.left;
        TreeNode q=root.right;
        TreeNode r=root.left.right.left;
        TreeNode out = lowestCommonAncestor(root,p,q);
        System.out.println(out.val);
        System.out.println(lowestCommonAncestor(root,p,r).val);


    }
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return null;
    }
    
}
