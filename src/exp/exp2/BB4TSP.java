package exp.exp2;

import java.util.*;

/**
 * 分支限界法求解TSP问题
 *
 * @author zl
 * @version 1.0
 * @date 2020/10/23
 */
public class BB4TSP {

    int NoEdge = -1; //表示没有边
    private int minCost = Integer.MAX_VALUE; //当前最小代价

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    /**
     * 最优解的下界是每一行最小的两个值的均值和
     *
     * @param matrix 邻接矩阵
     * @return 下界
     */
    public int getMin(int[][] matrix) {
        int m = 0;
        for (int[] ma : matrix) {
            int tempMin1 = Integer.MAX_VALUE;//最小
            int tempMin2 = Integer.MAX_VALUE;//次小
            for (int i : ma) {
                if (i < tempMin2 && i != -1 && i != 0) {
                    if (i >= tempMin1)
                        tempMin2 = i;
                    else {
                        tempMin2 = tempMin1;
                        tempMin1 = i;
                    }
                }
            }

            m += (tempMin1 + tempMin2) / 2;

        }
        return m;
    }

    /**
     * 获取上界，上界是用贪心求得
     *
     * @param matrix
     * @return
     */
    public int getMax(int[][] matrix) {
        //已经访问的城市
        ArrayList<Integer> visted = new ArrayList<Integer>();
        //接下来要访问的城市
        ArrayList<Integer> current = new ArrayList<Integer>();
        current.add(1);
        int sum = 0;
        while (!current.isEmpty()) {
            //c为当前出发的城市
            int c = current.get(0), tempMin = Integer.MAX_VALUE;
            current.remove(0);
            visted.add(c);
            int minCity = -1;//存储一行里面最小城市

            for (int i = 2; i <= matrix.length - 1; i++) {
                if (matrix[c][i] != -1 && matrix[c][i] < tempMin && !visted.contains(i)) {
                    tempMin = matrix[c][i];
                    minCity = i;
                }
            }

            if (minCity != -1) {
                sum += tempMin;
                current.add(minCity);
            }
        }
        sum+=matrix[visted.get(visted.size() - 1)][1];

        return sum;
    }


    Comparator<HeapNode> cmp = new Comparator<HeapNode>() {
        public int compare(HeapNode e1, HeapNode e2) {//从大到小排序
            //出队的时候从大到小
            return e2.lcost - e1.lcost;
        }
    };

    private PriorityQueue<HeapNode> priorHeap = new PriorityQueue<HeapNode>(100, cmp);//存储活节点
    private Vector<Integer> bestH = new Vector<Integer>();


    @SuppressWarnings("rawtypes")
    public static class HeapNode implements Comparable {
        //堆节点
        Vector<Integer> liveNode = new Vector<Integer>();//活结点
        int lcost; //代价的下界
        int level;//0-level的城市是已经排好的

        //构造方法
        public HeapNode(Vector<Integer> node, int lb, int lev) {

            liveNode.addAll(0, node);
            lcost = lb;
            level = lev;
        }

        @Override
        public int compareTo(Object x) {//升序排列, 每一次pollFirst
            int xu = ((HeapNode) x).lcost;
            if (lcost < xu) return -1;
            if (lcost == xu) return 0;
            return 1;
        }

        public boolean equals(Object x) {
            return lcost == ((HeapNode) x).lcost;
        }

    }

    /**
     * 计算部分解的下界.
     *
     * @param liveNode 城市的排列
     * @param level    当前确定的城市的个数.
     * @param cMatrix  邻接矩阵，第0行，0列不算
     */
    public int computeLB(Vector<Integer> liveNode, int level, int[][] cMatrix) {
        //TODO
        return -1;
    }

    /**
     * 计算TSP问题的最小代价的路径.
     *
     * @param cMatrix 邻接矩阵，第0行，0列不算
     * @param n       城市个数.
     */
    public int bb4TSP(int[][] cMatrix, int n) {
        //构造初始节点
        Vector<Integer> liveNode = new Vector<Integer>();//城市排列
        for (int i = 1; i <= n; i++) liveNode.add(i);
        int level = 1;//0-level的城市是已经排好的
        int lcost = computeLB(liveNode, level, cMatrix); //代价的下界
        while (level != n) {
            //TODO
            //参考优先队列，不停扩展节点,选取下一个节点
        }
        return minCost;
    }

    public static void main(String[] args) {
        Vector<Integer> n = new Vector<>();
        n.add(1);
        n.add(2);
        HeapNode h1 = new HeapNode(n, 5, 2);
        HeapNode h2 = new HeapNode(n, 2, 3);

        HeapNode h3 = new HeapNode(n, 3, 3);

        HeapNode h4 = new HeapNode(n, 6, 3);

        BB4TSP b = new BB4TSP();
        b.priorHeap.add(h1);
        b.priorHeap.add(h2);
        b.priorHeap.add(h3);
        b.priorHeap.add(h4);
        System.out.println(Arrays.toString(new PriorityQueue[]{b.priorHeap}));

    }


}
