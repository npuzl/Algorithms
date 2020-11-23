package Homework.homework3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxCline {
    int[][] adjacency;
    int[] out;
    int n;//点的个数
    int maxV = 0;
    Comparator<Node> cmp = new Comparator<Node>() {
        @Override
        public int compare(Node e1, Node e2) {//从大到小排序
            //出队的时候从大到小
            return e2.ub - e1.ub;
        }
    };
    private final PriorityQueue<Node> queue = new PriorityQueue<Node>(100, cmp);

    static class Node implements Cloneable {
        int ub;
        int level;//层数，从0开始，
        int[] sol;
        Node parent;

        Node() {
        }

        public Object clone() {
            Node node = null;
            try {
                node = (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            assert node != null;
            node.sol = sol.clone();
            //node.parent= (Node) parent.clone();
            return node;
        }

    }

    MaxCline(int[][] adjacency) {
        this.adjacency = adjacency;
        this.n = adjacency.length;
        Node node = new Node();
        node.level = -1;
        node.sol = new int[n];
        node.parent = null;
        node.ub = computeUB(node, 1);
        queue.offer(node);
        Solve();
    }

    private void Solve() {
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (int i = 1; i >= 0; i--) {
                if (!check(n.sol, n.level + 1, i))
                    continue;
                Node node = (Node) n.clone();
                node.level += 1;
                node.sol[node.level] = i;
                node.parent = n;
                node.ub = computeUB(node, i);
                if (node.level == this.n - 1) {//到达底部了
                    if (node.ub > this.maxV) {
                        maxV = node.ub;
                        out = node.sol.clone();
                        queue.removeIf(h -> h.ub < maxV);
                    }
                } else {
                    queue.offer(node);
                }
            }

        }

    }

    private int computeUB(Node node, int i) {
        if (node.level == -1)
            return n;
        int ub = node.parent.ub - 1;
        //for(int i=node.level+1;i<n;i++){
        //    ub+=1;
        //}
        return ub + i;
    }

    //sol的索引为level处为i时是否可以
    private boolean check(int[] solve, int level, int i) {
        //如果不加新点，那肯定是可以的
        if (i == 0) return true;
        //
        for (int j = 0; j <= level - 1; j++) {
            //如果前面有1，则记下1的索引
            int index = solve[j] == 1 ? j : -1;
            if (index == -1) continue;
            //如果level与index没有边，不行
            if (adjacency[index][level] == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] getOut() {
        int[] solution = new int[maxV];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (out[i] == 1)
                solution[r++] = i + 1;
        }
        return solution;
    }

    public static void testcase() {
        int[][] adjacency = new int[][]{
                new int[]{0, 1, 0, 1, 1},
                new int[]{1, 0, 1, 0, 1},
                new int[]{0, 1, 0, 0, 1},
                new int[]{1, 0, 0, 0, 1},
                new int[]{1, 1, 1, 1, 0}
        };

        MaxCline maxCline = new MaxCline(adjacency);
        System.out.println(Arrays.toString(maxCline.getOut()));
        System.out.println(maxCline.maxV);
    }

    public static void testcase2() {
        int[][] adjacency = new int[][]{
                new int[]{0, 1, 0, 1, 1},
                new int[]{1, 0, 1, 1, 1},
                new int[]{0, 1, 0, 0, 1},
                new int[]{1, 1, 0, 0, 1},
                new int[]{1, 1, 1, 1, 0}
        };

        MaxCline maxCline = new MaxCline(adjacency);
        System.out.println(Arrays.toString(maxCline.getOut()));
        System.out.println(maxCline.maxV);
    }

    public static void testcase3() {
        int[][] adjacency = new int[][]{
                new int[]{0, 1, 0, 1, 0},
                new int[]{1, 0, 0, 0, 1},
                new int[]{0, 0, 0, 0, 1},
                new int[]{1, 0, 0, 0, 1},
                new int[]{0, 1, 1, 1, 0}
        };

        MaxCline maxCline = new MaxCline(adjacency);
        System.out.println(Arrays.toString(maxCline.getOut()));
        System.out.println(maxCline.maxV);
    }

    public static void main(String[] args) {
        MaxCline.testcase();
        MaxCline.testcase2();
        MaxCline.testcase3();
    }
}
