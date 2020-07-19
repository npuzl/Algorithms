package summer.leetcode;

import java.lang.reflect.Array;
import java.util.*;

public class postOrderNTree {
    private static final Scanner SC = new Scanner(System.in);
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public static Node testTree(){


        Node node5=new Node(14);

        Node node6=new Node(111);
        Node node7=new Node(112);
        List<Node> list3 =Arrays.asList(node6,node7);
        Node node2=new Node(11,list3);

        Node node8=new Node(121);
        List<Node> list2 = Collections.singletonList(node8);
        Node node3=new Node(12,list2);

        Node node9=new Node(131);
        Node node10=new Node(132);
        List<Node> list1= Arrays.asList(node9,node10);
        Node node4=new Node(13,list1);

        List<Node> list4=Arrays.asList(node2,node3,node4,node5);

        return new Node(1,list4);
    }
    public static void main(String[] args) {
        Node root = testTree();
        List<Integer> list=postOrder(root);
        for(Integer i:list){
            System.out.println(i);
        }

    }
    public static List<Integer> postOrder(Node root) {
        //这个方法是对的，但是有一个测试用例过不去，就是输入root为空，输出应该也为空，但是这个输出是null
        List<Integer> list = new LinkedList<>();
        if(root==null) {
            return null;
        }
        if(root.children!=null){
        for(Node i:root.children) {
            list.addAll(postOrder(i));
        }}
        list.add(root.val);
        return list;
    }


}
