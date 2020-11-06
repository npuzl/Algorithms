package exp.exp3;

import java.util.Arrays;
import java.util.Random;

public class GAOperations {

    private int[][] iniPop;
    private int popNum;
    private int length;
    private int[][] matrix;
    private int[] code;
    private int codeNum;
    private int[] codeCount;
    private double[] fitness;
    private int[] bestSol;
    private int minCost = Integer.MAX_VALUE;

    private int factor(int a) {
        int sum = 1;
        for (int i = 1; i <= a; i++) {
            sum *= i;
        }
        return sum;
    }

    /**
     * 构造函数，设置种群大小和邻接矩阵
     *
     * @param popNum 种群大小
     * @param matrix 邻接矩阵
     * @param code   编码
     */
    public GAOperations(int popNum, int[][] matrix, int[] code) {

        this.popNum = popNum;
        this.matrix = matrix;
        this.code = code;
        this.length = matrix.length;
        this.codeNum = length;
        iniPop = new int[popNum][length];
        codeCount = new int[length];
        for (int i = 0; i < length; i++)
            codeCount[i] = 1;
        fitness = new double[popNum];
    }

    /**
     * 随机产生初始解，思路：先产生，后修复（也可以边产生边修复，如产生的位置的代码计数过多，则重新随机产生）.
     */
    public void randomInitialization() {
        if (popNum > factor(length - 1)) {
            System.err.println("初始种群大小一定要小于城市数n-1的阶乘！");
            System.exit(0);
        }
        int i, j;
        Random random = new Random();
        iniPop[0] = code;
        int count = 1;
        while (count < popNum) {
            int[] c = iniPop[count - 1].clone();
            //调转两次
            for (int t = 0; t < codeNum / 2; t++) {
                i = random.nextInt(codeNum - 1) + 1;
                j = random.nextInt(codeNum - 1) + 1;
                swap(c, i, j);
            }
            //用这种方法产生编码不需要修复
            //接下来去重
            int index = hasRepeat(c, 0, count - 1);
            if (index == -1) {
                iniPop[count] = c;
                count++;
            }
        }
        //System.out.println(Arrays.deepToString(iniPop));
    }


    /**
     * 计算个体的适应度
     *
     * @param pop 个体
     */
    public double computeFitness(int[] pop) {
        int start, end;
        double sum = 0;
        for (int i = 0; i < pop.length - 1; i++) {
            start = pop[i] - 1;
            end = pop[i + 1] - 1;
            if (matrix[start][end] == Integer.MAX_VALUE)
                return 0;
            else {
                sum += matrix[start][end];
            }
        }
        if (matrix[pop[pop.length - 1] - 1][0] == Integer.MAX_VALUE)
            return 0;
        else {
            sum += matrix[pop[pop.length - 1] - 1][0];
        }
        return 1 / sum;
    }

    /**
     * 轮盘赌
     */
    public void roundBet() {
        for (int i = 0; i < popNum; i++) {
            fitness[i] = computeFitness(iniPop[i]);
        }
        double fitSum = 0;
        for (int i = 0; i < popNum; i++)
            fitSum += fitness[i];

        //计算每一个被选上的概率
        double[] pro = new double[popNum];
        for (int i = 0; i < popNum; i++) {
            pro[i] = fitness[i] / fitSum;
        }

        Random random = new Random();
        for (int i = 0; i < popNum; i++) {
            if (!(random.nextDouble() < pro[i])) {
                //不遗传
                //那就要新建一个
                int[] c = new int[length];
                while (true) {
                    for (int j = 0; j < c.length; j++) {
                        c[j] = j + 1;
                    }
                    for (int k = 0; k < c.length / 2; k++) {
                        swap(c, random.nextInt(length - 1) + 1, random.nextInt(length - 1) + 1);
                    }
                    int index = hasRepeat(c, 0, iniPop.length - 1);
                    if (index == -1)
                        break;
                }
                iniPop[i] = c;
            } //else {}
            //遗传
        }

    }


    /**
     * 增加扰乱
     * 变异
     *
     * @param disPos 随机交换的位置数
     */
    public void disturbance(int disPos) {
        for (int[] pop : iniPop) {
            for (int i = 0; i < disPos / 2; i++) {
                int k = new Random().nextInt(length - 1) + 1;
                int m = new Random().nextInt(length - 1) + 1;
                swap(pop, k, m);
            }
        }
        //System.out.println(Arrays.deepToString(iniPop));
        //扰动
    }

    public void newGeneration() {
        roundBet();
        //随机交换的位置数目设置为城市数/5
        disturbance(length / 5);
        updateBest();
    }

    int gen = 1;

    private void updateBest() {
        for (int[] pop : iniPop) {
            int sum = 0;
            for (int i = 0; i < pop.length - 1; i++) {
                sum += matrix[pop[i] - 1][pop[i + 1] - 1];
            }
            sum += matrix[pop[pop.length - 1] - 1][0];
            if (sum < minCost) {
                bestSol = pop;
                minCost = sum;
            }
        }
        System.out.println("第" + (gen++) + "代的最优解为:" + getMinCost() + "\t最优解为:" + Arrays.toString(bestSol));

    }

    /**
     * 获取code在codes中的位置
     *
     * @param code    编码
     * @param codeNum 总编码数
     * @param codes   编码矩阵.
     */
    public int getCodePos(int code, int codeNum, int[] codes) {
        int pos = 0;
        for (; pos < codeNum; pos++) {
            if (code == codes[pos]) {
                return pos;
            }
        }
        return -1;
    }

    private void swap(int[] code, int i, int j) {
        int temp = code[i];
        code[i] = code[j];
        code[j] = temp;
    }

    private int hasRepeat(int[] code, int from, int to) {
        int index = -1;
        for (int i = from; i <= to; i++) {
            int repeat = 0;
            for (int j = 0; j < code.length; j++) {
                if (iniPop[i][j] == code[j]) {
                    repeat++;
                }
            }
            if (repeat == code.length)
                return i;
        }
        return index;
    }

    public void getBestSol(int generation) {
        randomInitialization();
        for (int i = 0; i < generation; i++)
            newGeneration();
    }

    public int getMinCost() {
        return minCost;
    }
}
