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
        /*
        Back4TSP back4TSP4 = new Back4TSP();
        int[][] d = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 1, -1, 22},
                {-1, 21, -1, -1, 14},
                {-1, 1, 40, -1, 17},
                {-1, 41, 80, 10, -1}
        };


        back4TSP4.backtrack4TSP(d, 4);
        System.out.println(back4TSP4.getShortestLength());
        back4TSP4.getRoutedSol();


        Back4TSP back4TSP = new Back4TSP();
        int[][] a = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 9, 19, 13},
                {-1, 21, -1, -1, 14},
                {-1, 1, 40, -1, 17},
                {-1, 41, 80, 10, -1}
        };

        int n = 4;
        back4TSP.backtrack4TSP(a, n);
        System.out.println(back4TSP.getShortestLength());
        back4TSP.getRoutedSol();

        int[][] b = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 30, 6, 4},
                {-1, 30, -1, 5, 10},
                {-1, 6, 5, -1, 20},
                {-1, 4, 10, 20, -1}
        };
        Back4TSP back4TSP2 = new Back4TSP();
        back4TSP2.backtrack4TSP(b, 4);
        System.out.println(back4TSP2.getShortestLength());
        back4TSP2.getRoutedSol();

        int[][] c = {{-1, -1, -1, -1, -1, -1},
                {-1, -1, 3, 1, 5, 8},
                {-1, 3, -1, 6, 7, 9},
                {-1, 1, 6, -1, 4, 2},
                {-1, 5, 7, 4, -1, 3},
                {-1, 8, 9, 2, 3, -1}
        };
        Back4TSP back4TSP3 = new Back4TSP();
        back4TSP3.backtrack4TSP(c,5);
        System.out.println(back4TSP3.getShortestLength());
        back4TSP3.getRoutedSol();
*/
        //Assert.assertEquals(34, back4TSP.bestc);

        //makeCompare(5);
        //makeCompare(20);
        compare();
    }

    public void makeCompare(int k) {
        int[][] matrix = generateLoads(k);

        for(int []m:matrix)
            System.out.println(Arrays.toString(m));

        long start1 = System.currentTimeMillis();
        Back4TSP back4TSP = new Back4TSP();
        back4TSP.backtrack4TSP(matrix, k);
        long end1 = System.currentTimeMillis();
        long run1 = end1 - start1;
        back4TSP.getRoutedSol();


        long start2 = System.currentTimeMillis();
        BB4TSP bB4TSP = new BB4TSP();
        bB4TSP.bb4TSP(matrix, k);
        long end2 = System.currentTimeMillis();
        long run2 = end2 - start2;
        System.out.println("当城市数为" + k + "时");
        System.out.println("回溯法运行时间为" + run1 + "ms" +" 运行结果为:"+back4TSP.getShortestLength()+
                "\n分支限界法运行时间为" + run2 + "ms "+"运行结果为："+bB4TSP.getMinCost());


        //Assert.assertEquals(bB4TSP.getMinCost(), back4TSP.bestc);

    }
    public static void compare() {
        int a[][] = {{-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, 410},
                {-1, -1, -1, 641, 825, -1},
                {-1, 324, 641, -1, -1, 683},
                {-1, 558, 825, -1, -1, 962},
                {-1, -1, 201, 683, 962, -1}};
        Back4TSP back4TSP = new Back4TSP();
        back4TSP.backtrack4TSP(a,5);
        System.out.println(back4TSP.getShortestLength());
        back4TSP.getRoutedSol();

        BB4TSP bB4TSP = new BB4TSP();
        bB4TSP.bb4TSP(a, 5);
        System.out.println(bB4TSP.getMinCost());
        System.out.println(bB4TSP.getPath());
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
        for(int i=0;i<matrix.length; i++){
            matrix[i][i]=-1;
        }
        //System.out.println(Arrays.deepToString(matrix));
        return matrix;
    }
   /*
    public static void main(String[] args) {
        int [][] a=generateLoads(6);
        for(int []b:a){System.out.println(Arrays.toString(b));}
    }
*/

}
