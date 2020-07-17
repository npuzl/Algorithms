package summer.leetcode;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author zl
 */
public class forOffer55I {
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
     * 字符串转换为树
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
        String input;
        input = SC.nextLine();
        TreeNode root = toTree(input);

        System.out.println(maxDepth(root));
    }

    //解决方法
    public static int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }

        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }



}
