package Homework.homework3;

import java.util.*;

/**
 * 这道题其实和最短路问题一样，邻接矩阵就是可交流为1，不可交流为0
 * 最后的答案就是最短路长度-1
 */
public class AnimalCommunicate {
    int n;//动物数量
    int m;//连接数量
    int[][] links;//连接
    int[][] adjacency;//邻接矩阵，对角线为0，不可达为-1，可达为1
    int[] ans;
    Queue<Node> queue = new LinkedList<>();

    AnimalCommunicate(int n, int m, int[][] link, int k, int[][] test) {
        this.n = n;
        this.m = m;
        adjacency = new int[n][n];
        for (int[] l : adjacency)
            Arrays.fill(l, -1);
        for (int i = 0; i < n; i++)
            adjacency[i][i] = 0;
        for (int i = 0; i < m; i++) {
            adjacency[link[i][0]][link[i][1]] = 1;
            adjacency[link[i][1]][link[i][0]] = 1;
        }

        //HashMap<Integer, Integer[]> answer = new HashMap<Integer, Integer[]>();
        ans = new int[k];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int i = 0; i < k; i++) {
            if (test[i][0] == test[i][1]) {
                ans[i] = 0;
                continue;
            }
            queue.clear();
            Node node = new Node();
            node.pos = test[i][0];
            node.reached.add(test[i][0]);
            for (int j = 0; j < n; j++) {
                if (j != test[i][0])
                    node.unreached.add(j);
            }
            queue.add(node);
            calculate(i, test[i][0], test[i][1]);
        }

    }


    private void calculate(int i, int res, int des) {
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (int j : n.unreached) {
                //如果父节点不可达，下一个
                if (adjacency[n.pos][j] == -1) {
                    continue;
                }
                //可达，创建节点
                Node node = (Node) n.clone();
                node.add(j);
                node.pos = j;
                //更新距离
                node.num += adjacency[n.pos][j];
                //如果到达目的地
                if (des == j) {
                    //如果距离更小
                    if (ans[i] > node.reached.size() - 2) {
                        ans[i] = node.reached.size() - 2;
                        queue.removeIf(h -> h.num > ans[i]);
                    }
                }else {
                queue.offer(node);}
            }
        }
    }

    static class Node implements Cloneable {
        ArrayList<Integer> reached = new ArrayList<Integer>();
        HashSet<Integer> unreached = new HashSet<Integer>();
        int num = 0;//到达此的翻译数
        int pos = 0;

        Node() {
        }

        @Override
        public Object clone() {
            Node node = null;
            try {
                node = (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            assert node != null;
            node.reached = (ArrayList<Integer>) this.reached.clone();
            node.unreached = (HashSet<Integer>) this.unreached.clone();
            return node;
        }

        public void add(int i) {
            reached.add(i);
            unreached.remove(i);
        }
    }

    public int[] getOut() {
        for (int i = 0; i < ans.length; i++)
            if (ans[i] == Integer.MAX_VALUE)
                ans[i] = -1;
        return ans;
    }

    public static void testcase() {
        int k = 2;
        int[][] test = new int[][]{
                new int[]{0, 0}
                , new int[]{0, 2}};
        int n = 3, m = 2;
        int[][] link = new int[][]{
                new int[]{0, 1},
                new int[]{1, 2}
        };
        AnimalCommunicate animalCommunicate = new AnimalCommunicate(n, m, link, k, test);
        System.out.println(Arrays.toString(animalCommunicate.getOut()));
    }

    public static void testcase2() {
        int k = 2;
        int[][] test = new int[][]{
                new int[]{0, 3}
                , new int[]{1, 4}};
        int n = 5, m = 5;
        int[][] link = new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{0, 3},
                new int[]{3, 2},
                new int[]{0, 2}
        };
        AnimalCommunicate animalCommunicate = new AnimalCommunicate(n, m, link, k, test);
        System.out.println(Arrays.toString(animalCommunicate.getOut()));
    }

    public static void main(String[] args) {
        AnimalCommunicate.testcase();
        AnimalCommunicate.testcase2();
    }


}
