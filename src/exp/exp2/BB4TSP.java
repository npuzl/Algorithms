package exp.exp2;

import java.util.*;


/**
 * 分支限界法求解TSP问题
 * 参考罗老师分支限界法讲TSP问题的那几页
 * 很清晰
 *
 * @author zl
 * @version 5.0
 * @date 2020/10/31
 */
public class BB4TSP {
    //表示没有边
    int noEdge = -1;
    //当前最小代价
    private int minCost = Integer.MAX_VALUE;
    private int maxCost = 0;

    public int getMinCost() {
        return minCost;
    }

    Vector<Integer> path = null;

    public int[] getPath() {
        int[] routes = new int[path.size() + 1];
        for (int i = 0; i < path.size(); i++)
            routes[i] = path.get(i);
        routes[routes.length - 1] = path.get(0);
        return routes;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    /**
     * 获取上界，上界是用贪心求得
     * TODO 这个函数有问题哈，其实这个也没啥用
     * 问题在于，如果最后一个节点回不去呢
     * 如果最后一个节点回不去，就回溯，找次小的节点，唉，这样还挺烦的
     *
     * @param matrix 邻接矩阵
     */
    public void getMax(int[][] matrix) {
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

            //存储一行里面最小城市
            int minCity = -1;

            for (int i = 2; i <= matrix.length - 1; i++) {
                if (matrix[c][i] != -1 && matrix[c][i] < tempMin && !visted.contains(i)) {
                    tempMin = matrix[c][i];
                    minCity = i;
                }
            }

            if (minCity != -1) {
                sum += tempMin;
                current.add(minCity);
            } else {
                //当贪心到一半无法形成回路
                maxCost = Integer.MAX_VALUE;
                return;
            }
        }
        if (matrix[visted.get(visted.size() - 1)][1] == -1) {
            maxCost = Integer.MAX_VALUE;
            return;
        }
        maxCost += sum + matrix[visted.get(visted.size() - 1)][1];

    }

    /**
     * 真正的上界应该用回溯法+贪心来做，
     * 比较麻烦，有时间再写，先用上面的不完备的上界
     *
     * @param matrix
     * @param tempPath
     * @param deepth
     * @param tempLength
     */
    public void getMax(int[][] matrix, int[] tempPath, int deepth, int tempLength) {

        if (deepth > matrix.length - 1) {
            maxCost = tempLength;
        } else {
            for (int j = deepth; j <= matrix.length - 1; j++) {
                /*
                if(){
                    swap(deepth,j,tempPath);
                    if(...){
                        ...

                    }
                    swap(deepth,j,tempPath);


                }
*/
            }
        }
    }

    private void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    Comparator<HeapNode> cmp = new Comparator<HeapNode>() {
        @Override
        public int compare(HeapNode e1, HeapNode e2) {//从小到大排序
            //出队的时候从小到大
            return e1.lcost - e2.lcost;
        }
    };

    /**
     * 存储活结点
     */
    private final PriorityQueue<HeapNode> priorHeap = new PriorityQueue<HeapNode>(100, cmp);

    @SuppressWarnings("rawtypes")
    public static class HeapNode implements Comparable, Cloneable {
        //堆节点
        int lcost; //子树费用的下界//优先级//就是lb//那个computeLB的lb
        int level;//0-level的城市是已经排好的//其实这也是第几层
        Vector<Integer> visted = new Vector<Integer>();
        Vector<Integer> unVisted = new Vector<Integer>();

        /**
         * @param lc  当前节点的下界
         * @param lev level
         * @param n   城市的数目
         */
        public HeapNode(int lc, int lev, int n) {
            lcost = lc;
            level = lev;
            for (int i = 1; i <= n; i++) {
                unVisted.add(i);
            }
        }

        /**
         * 在已访问的节点集合中增加一个节点，同时在未访问的节点集合删去该节点
         *
         * @param a 要增加的节点
         */
        public void addVisted(int a) {
            visted.add(a);
            for (int i = 0; i < unVisted.size(); i++) {
                if (unVisted.get(i) == a) {
                    unVisted.remove(i);
                }
            }
        }

        /**
         * 实现堆节点的深度拷贝方法，便于创建新节点
         *
         * @return 堆节点
         */
        @Override
        public Object clone() {
            HeapNode heapNode = null;
            try {
                heapNode = (HeapNode) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            assert heapNode != null;
            heapNode.unVisted = (Vector<Integer>) unVisted.clone();
            heapNode.visted = (Vector<Integer>) visted.clone();
            // heapNode.priorNode= (Vector<Integer>) priorNode.clone();
            return heapNode;
        }

        @Override
        public int compareTo(Object x) {//升序排列, 每一次pollFirst
            int xu = ((HeapNode) x).lcost;
            return Integer.compare(lcost, xu);
        }

        @Override
        public boolean equals(Object x) {
            if (x == null) {
                return false;
            }
            if (this == x) {
                return true;
            }
            if (getClass() != x.getClass()) {
                return false;
            }
            return lcost == ((HeapNode) x).lcost;
        }
    }

    /**
     * 计算部分解的下界.
     *
     * @param level   当前确定的城市的个数.
     * @param cMatrix 邻接矩阵，第0行，0列不算
     */
    public int computeLB(HeapNode heapNode, int level, int[][] cMatrix) {
        Vector<Integer> visted = heapNode.visted;//这里面存的是已经确定的节点
        Vector<Integer> unVisted = heapNode.unVisted;//存未确定的节点
        int n = cMatrix.length - 1;//总城市数目
        int vistedSize = visted.size();
        //下面计算那个求和式子
        int tempSum = 0;
        for (int i = 0; i < level - 1; i++) {
            tempSum += 2 * cMatrix[visted.get(i)][visted.get(i + 1)];
        }
        //如果是最后一个节点，还要加上最后一个节点到头的距离
        if (vistedSize == n) {
            tempSum += 2 * cMatrix[visted.get(vistedSize - 1)][visted.get(0)];
        }

        //计算中间两项//对于有向图，要考虑从1出发还是回到1，
        int tempMin1 = Integer.MAX_VALUE;
        int tempMin2 = Integer.MAX_VALUE;
        for (int j : unVisted) {
            //从r1出发的最小值
            if (cMatrix[visted.get(0)][j] < tempMin1 && cMatrix[visted.get(0)][j] != -1) {
                tempMin1 = cMatrix[visted.get(0)][j];
            }
            //从别的地方出发回到rk的最小值
            if (cMatrix[j][visted.get(vistedSize - 1)] != -1 && cMatrix[j][visted.get(vistedSize - 1)] < tempMin2) {
                tempMin2 = cMatrix[j][visted.get(vistedSize - 1)];
            }
        }
        long tempMin = (long) tempMin1 + (long) tempMin2;
        tempMin1 = Integer.MAX_VALUE;
        tempMin2 = Integer.MAX_VALUE;
        for (int j : unVisted) {
            //从别的地方回到r1的最小值
            if (cMatrix[j][visted.get(0)] < tempMin1 && cMatrix[j][visted.get(0)] != -1) {
                tempMin1 = cMatrix[j][visted.get(0)];
            }
            //从rk出发的最小值
            if (cMatrix[visted.get(vistedSize - 1)][j] != -1 && cMatrix[visted.get(vistedSize - 1)][j] < tempMin2) {
                tempMin2 = cMatrix[visted.get(vistedSize - 1)][j];
            }
        }
        //前提是unvisted非空，如果为空还加，则会有问题

        if (unVisted.size() != 0)
            tempSum += Math.min((long) tempMin1 + (long) tempMin2, tempMin);

        //最后那个求和项目
        for (int i : unVisted) {
            //找第i行和第i列最小的元素
            int lineMin = Integer.MAX_VALUE;
            for (int j = 0; j <= n; j++) {
                if (cMatrix[i][j] != -1 && cMatrix[i][j] != 0 && cMatrix[i][j] < lineMin)
                    lineMin = cMatrix[i][j];
            }
            if (lineMin != Integer.MAX_VALUE)
                tempSum += lineMin;

            int colMin = Integer.MAX_VALUE;
            for (int[] matrix : cMatrix) {
                if (matrix[i] != -1 && matrix[i] != 0 && matrix[i] < colMin)
                    colMin = matrix[i];
            }
            if (colMin != Integer.MAX_VALUE)
                tempSum += colMin;
        }
        return tempSum / 2;
    }

    /**
     * 计算TSP问题的最小代价的路径.
     *
     * @param cMatrix 邻接矩阵，第0行，0列不算
     * @param n       城市个数.
     */
    public void bb4TSP(int[][] cMatrix, int n) {
        /*
        int []tempPath=new int[n];
        for (int i = 0; i < n; i++) {
            tempPath[i]=i;
        }
        //先获取上界//这一部分代码有问题，先不用了
        getMax(cMatrix,tempPath,2,Integer.MAX_VALUE);
        */
        //上界为 maxCost

        getMax(cMatrix);
        //0-level的城市是已经排好的
        int level = 1;
        HeapNode heapNode = new HeapNode(0, level, n);
        heapNode.addVisted(1);//添加已访问的城市1
        heapNode.lcost = computeLB(heapNode, level, cMatrix);

        priorHeap.add(heapNode);
        while (!priorHeap.isEmpty()) {
            HeapNode toExtend = priorHeap.poll();//队列中等待扩展的节点
            for (int i : toExtend.unVisted) {//进行节点拓展
                //首先判定新节点与已访问的节点有无通路，无的话就不创建新节点
                if (cMatrix[toExtend.visted.get(toExtend.visted.size() - 1)][i] == -1)
                    continue;
                HeapNode node = (HeapNode) toExtend.clone();//先根据父节点复制一个，深拷贝
                node.addVisted(i);//复制的节点增加已访问的城市
                node.level++;//新的节点层数加1
                node.lcost = computeLB(node, node.level, cMatrix);//新的节点下界换一下
                //如果大于上界，直接不考虑了
                if(node.lcost>maxCost)
                    continue;
                int vistedSize = node.visted.size();
                //如果到了叶节点且与首节点有通路 或者未到叶节点
                if (vistedSize < n || (vistedSize == n && cMatrix[i][1] != -1)) {
                    priorHeap.add(node);//加入队列
                    // 如果新扩展的子节点到达了叶节点
                    if (toExtend.level + 1 == n && node.lcost < minCost) {
                        minCost = node.lcost;
                        path = node.visted;
                    }
                }

            }
            //接下来进行剪枝//删除lcost大于minCost的节点
            priorHeap.removeIf(h -> h.lcost > minCost);
        }
        //return minCost;
    }

}
