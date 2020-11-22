package Homework.homework3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这道题用分支限界法还是挺慢的
 */
public class EatingShells {
    long x0;
    int LB = Integer.MAX_VALUE;

    static class Node {
        long x;
        int time = 0;

        Node(long x) {
            this.x = x;
        }

        Node(int i, long fatherX0, int fatherTime) {
            if (i == 0)
                x = fatherX0 * 4 + 3;
            else
                x = fatherX0 * 8 + 7;
            time = fatherTime + 1;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public long getX() {
            return x;
        }
    }

    //queue里面offer是添加，poll是删除
    Queue<Node> queue = new LinkedList<>();

    EatingShells(long x0) {
        this.x0 = x0;
        queue.add(new Node(x0));
        Solve();
    }

    private void Solve() {
        //队列不为空时
        while (!queue.isEmpty()) {
            //队列顶端的先搞出来
            Node n = queue.poll();

            for (int i = 0; i <= 1; i++) {
                //根据搞出来的创建新节点，更新X和Time
                Node node = new Node(i, n.getX(), n.getTime());
                //如果大于下界或者大于约束，都不用入队
                if (node.getTime() > LB || node.getTime() > 100000) {
                } else {
                    //如果满足需求且次数小于LB，更新LB
                    if (node.getX() % 1000000007 == 0) {
                        //如果次数小于LB
                        if (LB > node.getTime()) {
                            LB = node.getTime();
                        }
                    } else {
                        //不满足需求
                        queue.offer(node);
                    }
                }
            }
        }
    }

    public int getLB() {
        if (LB == Integer.MAX_VALUE)
            return -1;
        return LB;
    }

    public static void testcase() {
        long x0 = 125000000;
        EatingShells e = new EatingShells(x0);
        System.out.println(e.getLB());
    }

    public static void main(String[] args) {
        EatingShells.testcase();
    }
}
