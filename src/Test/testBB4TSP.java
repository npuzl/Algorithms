package Test;

import org.junit.Assert;
import org.junit.Test;
import exp.exp2.*;

import java.util.Arrays;

public class testBB4TSP {

    @Test
    public void testbb4TSP() {
        int n = 2;
        int[][] matrix = Back4TSPTest.generateLoads(n);
        System.out.println("邻接矩阵为：");
        for (int[] m : matrix)
            System.out.println(Arrays.toString(m));

        long start2 = System.currentTimeMillis();
        BB4TSP bB4TSP = new BB4TSP();
        bB4TSP.bb4TSP(matrix, n);
        long end2 = System.currentTimeMillis();
        long run2 = end2 - start2;
        System.out.println("当城市数为" + n + "时");
        System.out.println("分支限界法运行时间为" + run2 + "ms " + "运行结果为：" + bB4TSP.getMinCost()
                + "\n路线为" + Arrays.toString(bB4TSP.getPath()));




        /*
        BB4TSP bb4TSP = new BB4TSP();
        int[][]b = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 30, 6, 4},
                {-1, 30, -1, 5, 10},
                {-1, 6, 5, -1, 20},
                {-1, 4, 10, 20, -1}
        };
        int[][] c = {{-1, -1, -1, -1, -1, -1},
                {-1, -1, 3, 1, 5, 8},
                {-1, 3, -1, 6, 7, 9},
                {-1, 1, 6, -1, 4, 2},
                {-1, 5, 7, 4, -1, 3},
                {-1, 8, 9, 2, 3, -1}

        };*/
        int[][] a =
                {{-1, -1, -1, -1, -1},
                {-1, -1, 9, 19, 13},
                {-1, 21, -1, -1, 14},
                {-1, 1, 40, -1, 17},
                {-1, 41, 80, 10, -1}};
        BB4TSP bb4TSP = new BB4TSP();
        bb4TSP.bb4TSP(a, 4);
        System.out.println(bb4TSP.getMinCost());
        System.out.println(Arrays.toString(bb4TSP.getPath()));
/*
        BB4TSP bb4TSP2=new BB4TSP();
        bb4TSP2.bb4TSP(b,4);
        System.out.println(bb4TSP2.getMinCost());
        System.out.println(bb4TSP2.getPath());

        BB4TSP bb4TSP3=new BB4TSP();
        bb4TSP3.bb4TSP(c,5);
        System.out.println(bb4TSP3.getMinCost());
        System.out.println(bb4TSP3.getPath());

        int[][] d = {
                {-1, -1, -1, -1, -1},
                {-1, -1, 1, -1, 22},
                {-1, 21, -1, -1, 14},
                {-1, 1, 40, -1, 17},
                {-1, 41, 80, 10, -1}
        };

        BB4TSP bb4TSP4=new BB4TSP();
        bb4TSP4.bb4TSP(d,4);
        System.out.println(bb4TSP4.getMinCost());
        System.out.println(bb4TSP4.getPath());
*/


    }
}
