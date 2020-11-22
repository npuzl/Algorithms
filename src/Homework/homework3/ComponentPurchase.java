package Homework.homework3;


import java.util.*;

public class ComponentPurchase {
    /**
     * 机器的部件数目
     */
    int n;
    /**
     * m个不同的供应商
     */
    int m;
    /**
     * 总预算
     */
    int cost;
    /**
     * w[i][j]为从j处购得零件i的重量
     */
    int[][] w;
    /**
     * c[i][j]为从j处购得零件i的价格
     */
    int[][] c;
    int LB = Integer.MAX_VALUE;
    Integer[] choice;
    Queue<Node> queue = new LinkedList<>();

    ComponentPurchase(int n, int m, int cost, int[][] w, int[][] c) {
        this.n = n;
        this.m = m;
        this.cost = cost;
        this.w = w;
        this.c = c;
        choice = new Integer[n];
        if (m < n) {
            System.out.println("供应商的数目少于零件数目");
            return;
        }
        Node first = new Node();
        for (int i = 0; i < m; i++)
            first.unused.add(i);
        queue.add(first);
        calculate();
    }

    static class Node implements Cloneable {
        ArrayList<Integer> used = new ArrayList<>();
        HashSet<Integer> unused = new HashSet<Integer>();//未访问的可以用hashset，速度快一点，不用查找
        int cost = 0;
        int weight = 0;
        int num = -1;//第几个零件

        Node() {
        }

        Node(int cost, int weight, int num) {
            this.cost = cost;
            this.weight = weight;
            this.num = num;
        }

        @Override
        public Object clone() {
            Node n = null;
            try {
                n = (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            assert n != null;
            n.used = (ArrayList<Integer>) this.used.clone();
            n.unused = (HashSet<Integer>) this.unused.clone();
            return n;
        }

        public void add(int i) {
            used.add(i);
            unused.remove(i);
        }
    }

    public void calculate() {
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (int i : n.unused) {
                Node node = (Node) n.clone();
                node.add(i);
                node.num += 1;
                node.cost += c[node.num][i];
                node.weight += w[node.num][i];
                //更新了层数，价格，重量
                //如果总价格大于cost限制，这个节点就不要了
                if (node.cost > cost)
                    continue;
                //如果空了
                if (node.unused.isEmpty()) {
                    //如果节点weight小于LB，更新并移除其他的大于LB的节点
                    if (node.weight < LB) {
                        LB = node.weight;
                        node.used.toArray(choice);
                        queue.removeIf(h -> h.weight > LB);
                    }
                }else{
                //入队
                queue.offer(node);}
            }
        }
    }

    public int getMinimize() {
        return LB;
    }

    public Integer[] getChoice() {
        for (int i = 0; i < choice.length; i++) {
            choice[i] += 1;
        }
        return choice;
    }

    public static void testCase() {
        int n = 3, m = 3, cost = 7;
        int[][] w = new int[][]{new int[]{1, 2, 3}, new int[]{3, 2, 1}, new int[]{2, 3, 2}};
        int[][] c = new int[][]{new int[]{1, 2, 3}, new int[]{5, 4, 2}, new int[]{2, 1, 2}};
        ComponentPurchase componentPurchase = new ComponentPurchase(n, m, cost, w, c);
        System.out.println(Arrays.toString(componentPurchase.getChoice()));
        System.out.println(componentPurchase.getMinimize());
    }

    public static void main(String[] args) {
        ComponentPurchase.testCase();
    }
}
