package Homework.homework3;

import exp.exp2.BB4TSP;
import lessons.DynamicProgramming.ZeroNBag;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * 用的测试用例是PPT上的
 * 01背包问题两种分支限界界函数
 * 第一种就是PPT上的，上界为  先把物品重量大于余量的去掉，剩下的物品按照价值比重大小依次装包
 * 还有一种界函数 ub=v+(W-w)*(v_{i+1}/w_{i+1})
 * 我们采用第二种
 * 使用优先级队列
 * 界函数越大的越先
 */
public class ZeroOneBag {
    int n;
    double capacity;
    double[] weights;
    double[] values;
    double UB = 0;
    int[] out;
    Comparator<Node> cmp = new Comparator<Node>() {
        @Override
        public int compare(Node e1, Node e2) {//从大到小排序
            //出队的时候从大到小
            return (int) (e2.ub - e1.ub);
        }
    };
    private final PriorityQueue<Node> queue = new PriorityQueue<Node>(100, cmp);

    ZeroOneBag(int n, double capacity, double[] weights, double[] values) {
        this.n = n;
        this.capacity = capacity;
        this.weights = weights;
        this.values = values;
        Node node = new Node();
        node.w = 0;
        node.v = 0;
        node.num = -1;
        node.exist = new int[n];
        node.ub = computeUB(node);
        queue.add(node);
        calculate();
    }

    private void calculate() {
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (int i = 1; i>=0; i--) {
                Node node = (Node) n.clone();
                node.num += 1;
                node.w += i * weights[node.num];
                if (node.w > capacity)
                    continue;
                node.v += i * values[node.num];
                node.exist[node.num] = i;
                node.ub = computeUB(node);
                if (node.num == this.n - 1) {
                    if (node.ub > UB) {
                        UB = node.ub;
                        out = node.exist.clone();
                        queue.removeIf(h->h.ub<UB);
                    }
                } else {
                    queue.add(node);
                }
            }
        }

    }

    private double computeUB(Node node) {
        if (node.num == n - 1) {
            return node.v;
        }
        return node.v + (capacity - node.w) * (values[node.num + 1] / weights[node.num + 1]);
    }

    static class Node implements Cloneable {
        double ub;
        double w;//累计重量
        double v;//累计价值
        int num;//序号，0就是第一个物品，1是第二个物品
        int[] exist;//全部的购买情况

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
            node.exist=exist.clone();
            return node;
        }

    }

    public double getMax() {
        return UB;
    }

    public int[] getOut() {
        return out;
    }

    public static void testcase() {
        int n = 3;
        double c = 30;
        double[] w = new double[]{16, 15, 15};
        double[] v = new double[]{28, 30, 30};
        ZeroOneBag zeroOneBag = new ZeroOneBag(n, c, w, v);
        System.out.println(zeroOneBag.getMax() + "\n" + Arrays.toString(zeroOneBag.getOut()));

    }
    public static void testcase2() {
        int n = 4;
        double c = 30;
        double[] w = new double[]{40,16, 15, 15};
        double[] v = new double[]{160,45, 25, 25};
        ZeroOneBag zeroOneBag = new ZeroOneBag(n, c, w, v);
        System.out.println(zeroOneBag.getMax() + "\n" + Arrays.toString(zeroOneBag.getOut()));
    }
    public static void testcase3() {
        int n = 4;
        double c =10;
        double[] w = new double[]{4,7,5,3};
        double[] v = new double[]{40,42,25,12};
        ZeroOneBag zeroOneBag = new ZeroOneBag(n, c, w, v);
        System.out.println(zeroOneBag.getMax() + "\n" + Arrays.toString(zeroOneBag.getOut()));

    }
    public static void main(String[] args) {
        ZeroOneBag.testcase();
        ZeroOneBag.testcase2();
        ZeroOneBag.testcase3();
    }
}
