package Homework.homework1;

import java.util.*;

/**
 * @author zl
 */
public class Dijkstra {
    /**
     * 本作业在github上管理，做题记录都在github上，
     * github地址：https://github.com/npuzl/Algorithms
     * 老师如果打开链接了的话麻烦点个star呗
     * 如果有错误，可以提Issues，谢谢！
     * 迪杰斯特拉算法求解最短路
     * @param matrix 初始矩阵
     * @param path 得到的路线
     * @param source 源点
     * @return 距离矩阵
     */
    public static double[] dijkstra(double[][] matrix, int[][] path, int source) {
        HashSet<Integer> resource = new HashSet<>();
        HashSet<Integer> unknown = new HashSet<>();
        resource.add(source);
        for (int i = 0; i < matrix.length; i++) {
            if (i != source) {
                unknown.add(i);
            }
        }
        int i = 0;
        while (!unknown.isEmpty()){
            double min = Double.MAX_VALUE;
            int from = 0;
            int to = 0;

            for (int u : unknown) {
                for (int r : resource) {
                    if (matrix[u][r] + matrix[source][r] < min) {
                        min = matrix[u][r];
                        from = r;
                        to = u;
                    }
                }
            }
            matrix[source][to] = matrix[source][from] + min;
            matrix[to][source] = matrix[source][to];
            path[i][0] = from;
            path[i][1] = to;
            i++;
            resource.add(to);
            unknown.remove(to);
        }
        return matrix[source];
    }

    public static void generatePath(int[][] path, int resource, int destination) {
        ArrayList<Integer> paths=new ArrayList<>();
        paths.add(destination);
        while(destination!=resource){
            for(int []p:path) {
                if (p[1]==destination){
                    paths.add(p[0]);
                    destination=p[0];
                }
            }
        }
        for(int i=paths.size()-1;i>=1;i--){
            System.out.print(paths.get(i)+"->");
        }
        System.out.println(paths.get(0));
    }

    public static double[][] generateMatrix(int n, int[][] locals, int[][] links) {
        double[][] matrix = new double[n][n];
        for (int[] link : links) {
            matrix[link[0]][link[1]] = Math.sqrt(Math.pow((locals[link[0]][0] - locals[link[1]][0]), 2)
                    + Math.pow((locals[link[0]][1] - locals[link[1]][1]), 2));
        }
        //下面这个二层循环会让这个图变成无向图
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = matrix[j][i];
                }
                if (i != j && matrix[i][j] == 0) {
                    matrix[i][j] = Double.MAX_VALUE;
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();
        int[][] locals = new int[n][2];
        //生成n个城市坐标
        for (int i = 0; i < n; i++) {
            locals[i][0] = random.nextInt(50);
            locals[i][1] = random.nextInt(50);
        }
        //生成城市之间的连接
        int[][] links = new int[(int) (n-1) * (n - 1) / 3][2];
        for (int i = 0; i < (int) (n-1) * (n - 1) / 3; i++) {
            int city1 = random.nextInt(n);
            int city2 = random.nextInt(n);
            while (city1 == city2) {
                city1 = random.nextInt(n);
            }
            links[i][0] = city1;
            links[i][1] = city2;
        }
        double[][] matrix = generateMatrix(n, locals, links);

        for (int[] l : locals) {
            System.out.println(Arrays.toString(l));
        }
        for (int[] l : links) {
            System.out.println(Arrays.toString(l));
        }
        for (double[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }

        int[][] path = new int[n - 1][2];
        System.out.println(Arrays.toString(dijkstra(matrix, path, 0)));
        for(int i=1;i<n;i++) {
            generatePath(path,0,i);
        }
        System.out.println(Arrays.deepToString(path));


        int num = 4;
        double[][] matrix2 = new double[][]{new double[]{0, 1, 3, Double.MAX_VALUE}, new double[]{1, 0, Double.MAX_VALUE, 2},
                new double[]{3, Double.MAX_VALUE, 0, 2}, new double[]{Double.MAX_VALUE, 2, 2, 0}
        };
        int[][] path2 = new int[num - 1][2];
        System.out.println(Arrays.toString(dijkstra(matrix2, path2, 0)));
        generatePath(path2,0,3);
    }

}
