package Test;

import exp.exp2.BB4TSP;
import org.junit.Assert;
import org.junit.Test;
import exp.exp2.Back4TSP;

import java.util.Arrays;
import java.util.Random;

public class Back4TSPTest {

    @Test
    public void testBacktrack4TSP() {
        Back4TSP back4TSP = new Back4TSP();
        int[][] b = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 9, 19, 13},
                {-1, 21, -1, -1, 14},
                {-1, 1, 40, -1, 17},
                {-1, 41, 80, 10, -1}
        };
        int n = 4;
        back4TSP.backtrack4TSP(b, n);
        System.out.println(back4TSP.getShortestLength());
        back4TSP.getRoutedSol();

        int[][] c = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 30, 6, 4},
                {-1, 30, -1, 5, 10},
                {-1, 6, 5, -1, 20},
                {-1, 4, 10, 20, -1}
        };
        Back4TSP back4TSP2 = new Back4TSP();
        back4TSP2.backtrack4TSP(c, 4);
        System.out.println(back4TSP2.getShortestLength());
        back4TSP2.getRoutedSol();


        //Assert.assertEquals(34, back4TSP.bestc);

        //makeCompare(20);
        //makeCompare(20);

    }

    public void makeCompare(int k) {
        int[][] matrix = generateLoads(k);

        long start1 = System.currentTimeMillis();
        Back4TSP back4TSP = new Back4TSP();
        back4TSP.backtrack4TSP(matrix, k);
        long end1 = System.currentTimeMillis();
        long run1 = end1 - start1;

        long start2 = System.currentTimeMillis();
        BB4TSP bB4TSP = new BB4TSP();
        //bB4TSP.bb4TSP(matrix, k);
        long end2 = System.currentTimeMillis();
        long run2 = end2 - start2;
        System.out.println("当城市数为" + k + "时");
        System.out.println("回溯法运行时间为" + run1 + "ms\n分支限界法运行时间为" + run2 + "ms");


        //Assert.assertEquals(bB4TSP.getMinCost(), back4TSP.bestc);

    }

    public static int[][] generateLoads(int n) {
        Random random = new Random();
        int[][] cities = new int[n][2];
        for (int i = 0; i < n; i++) {
            cities[i][0] = random.nextInt(1000);
            cities[i][1] = random.nextInt(1000);
        }
        int[][] links = new int[(int) (n + n * n) / 2][2];
        for (int i = 0; i < links.length; i++) {
            links[i][0] = random.nextInt(n);
            links[i][1] = random.nextInt(n);
        }
        int[][] matrix = new int[n + 1][n + 1];
        for (int[] l : links) {
            matrix[l[0] + 1][l[1] + 1] = (int) Math.sqrt(Math.pow((cities[l[0]][0] - cities[l[1]][0]), 2) + Math.pow((cities[l[0]][1] - cities[l[1]][1]), 2));
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i != j && matrix[i][j] == 0) {
                    matrix[i][j] = -1;
                }
            }
        }
        matrix[0][0] = -1;
        return matrix;
    }
   /*
    public static void main(String[] args) {
        int [][] a=generateLoads(6);
        for(int []b:a){System.out.println(Arrays.toString(b));}
    }
*/

}
