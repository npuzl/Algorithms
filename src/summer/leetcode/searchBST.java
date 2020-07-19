package summer.leetcode;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class searchBST {
    private static final Scanner SC = new Scanner(System.in);
    /*
    sc.next();//不接受空格，在接收到有效数据前，所有的空格或者tab键等输入被忽略，若有有效数据，则遇到这些键退出
    sc.nextLine();//nextLine()可以接收空格或者tab键，其输入应该以enter键结束。
    sc.nextInt();
    sc.nextFloat();
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @return TreeNode
     * @author zl
     * 字符串转换为树，层次遍历
     * 输入格式 -1,2,null,3
     * 作为字符串输入
     */
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

    //主函数
    public static void main(String[] args) {
        TreeNode out=searchBST(toTree(SC.nextLine()),SC.nextInt());
        levelPrint(out);
    }

    //解决方法//递归版本
    public static TreeNode searchBST(TreeNode root,int target) {
        TreeNode out;
        if(root==null) {
            return null;
        }else if(root.val==target){
            return root;
        }else {
            out=searchBST(root.left, target);
            if(out==null||out.val!=target) {
                out=searchBST(root.right, target);
            }
        }
        return out;
    }

    //层次遍历输出
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
