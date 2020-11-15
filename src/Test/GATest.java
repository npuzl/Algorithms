package Test;

import exp.exp2.Back4TSP;
import org.junit.Test;
import exp.exp3.*;

import java.util.Arrays;
import java.util.Random;

public class GATest {

    @Test
    public void Test() {
        int popNum = 5;//种群大小
        int[] codes = {1, 2, 3, 4, 5};
        int[][] a = {
                {100, 3, 1, 5, 8},
                {3, 100, 6, 7, 9},
                {1, 6, 100, 4, 2},
                {5, 7, 4, 100, 3},
                {8, 9, 2, 3, 100}
        };
        GAOperations gaOperations = new GAOperations(popNum, a, codes);
        gaOperations.getBestSol(100);

    }

    @Test
    public void compareBack4AndGA() {
        int cityNum = 15;
        int popNum = 100;
        int[] code = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            code[i] = i + 1;
        }
        int[][] matrixGA = new int[cityNum][cityNum];
        int[][] matrixBack4 = new int[cityNum + 1][cityNum + 1];
        generateMatrix(matrixGA, matrixBack4, cityNum);


        GAOperations ga = new GAOperations(popNum, matrixGA, code);


        ga.getBestSol(5000);


        Back4TSP back4TSP = new Back4TSP();
        back4TSP.backtrack4TSP(matrixBack4, cityNum);
        System.out.println(back4TSP.getShortestLength());
        System.out.println(Arrays.toString(back4TSP.getRoutedSol()));

    }

    private void generateMatrix(int[][] matrixGA, int[][] matrixBack4, int n) {
        Random random = new Random();
        int[][] cities = new int[n][2];
        for (int i = 0; i < n; i++) {
            cities[i][0] = random.nextInt(1000);
            cities[i][1] = random.nextInt(1000);
        }
        int[][] links = new int[(n + 2 * n * n) / 2][2];
        for (int i = 0; i < links.length; i++) {
            links[i][0] = random.nextInt(n);
            links[i][1] = random.nextInt(n);
        }
        for (int[] l : links) {
            matrixBack4[l[0] + 1][l[1] + 1] = (int) Math.sqrt(Math.pow((cities[l[0]][0] - cities[l[1]][0]), 2) + Math.pow((cities[l[0]][1] - cities[l[1]][1]), 2));
            matrixBack4[l[1] + 1][l[0] + 1] = matrixBack4[l[0] + 1][l[1] + 1];
            matrixGA[l[0]][l[1]] = matrixBack4[l[0] + 1][l[1] + 1];
            matrixGA[l[1]][l[0]] = matrixGA[l[0]][l[1]];
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i != j && matrixBack4[i][j] == 0) {
                    matrixBack4[i][j] = -1;
                }
            }
        }
        matrixBack4[0][0] = -1;
        for (int i = 0; i < matrixBack4.length; i++) {
            matrixBack4[i][i] = -1;
        }

        for (int i = 0; i < matrixGA.length; i++) {
            for (int j = 0; j < matrixGA.length; j++) {
                if (matrixGA[i][j] == 0)
                    matrixGA[i][j] = 9999999;
            }
        }

        //System.out.println(Arrays.deepToString(matrix));

    }








	/*
	@Test
	public void TestRandomInitialization()
	{		
		gaOperations.RandomInitialization(popNum, length, codes, codeNum, codeCount, iniPop);
		int i, j;
		int[] nJs = new int[codeNum];//统计每个编码产生的数量
		for(i = 0; i < popNum; i++)
		{
			for(j = 0; j < codeNum; j++)
			{
				nJs[j] = 0;
			}
			for(j = 0; j < length; j++) //统计每个code的数量
			{
				int pos = gaOperations.getCodePos(iniPop[i][j], codeNum, codes);//获取code 在codes中的位置
				nJs[pos]++;
			}
			for(j = 0; j < codeNum; j++)
			{
				Assert.assertEquals(nJs[j], codeCount[j]);
			}
		}
	}
	
	@Test
	public void TestComputeFitness()
	{

		int[] pop = {1, 3, 5, 4, 2};
		double fit = gaOperations.computeFitness(pop, length, a);
		Assert.assertTrue(Math.abs(fit-1/16.0) < 0.0001);
	}
	
	@Test
	public void TestRoundBet()
	{
		gaOperations.RandomInitialization(popNum, length, codes, codeNum, codeCount, iniPop);
		int i, j;
		int[] nJs = new int[codeNum];//统计每个编码产生的数量
		double[] fitness = new double[popNum];
		for(i = 0; i < popNum; i++)
		{
			fitness[i] = gaOperations.computeFitness(iniPop[i], length, a);
		}
		gaOperations.roundBet(popNum, length, iniPop, fitness,a);
		for(i = 0; i < popNum; i++)
		{
			for(j = 0; j < codeNum; j++)
			{
				nJs[j] = 0;
			}
			for(j = 0; j < length; j++) //统计每个code的数量
			{
				int pos = gaOperations.getCodePos(iniPop[i][j], codeNum, codes);//获取code 在codes中的位置
				nJs[pos]++;
			}
			for(j = 0; j < codeNum; j++)
			{
				Assert.assertEquals(nJs[j], codeCount[j]);
			}
		}
	}

	@Test
	public void TestDisturbance()
	{
		gaOperations.RandomInitialization(popNum, length, codes, codeNum, codeCount, iniPop);
		int i, j;
		int[] nJs = new int[codeNum];//统计每个编码产生的数量
		gaOperations.Disturbance(iniPop, popNum, length, 5);
		for(i = 0; i < popNum; i++)
		{
			for(j = 0; j < codeNum; j++)
			{
				nJs[j] = 0;
			}
			for(j = 0; j < length; j++) //统计每个code的数量
			{
				int pos = gaOperations.getCodePos(iniPop[i][j], codeNum, codes);//获取code 在codes中的位置
				nJs[pos]++;
			}
			for(j = 0; j < codeNum; j++)
			{
				Assert.assertEquals(nJs[j], codeCount[j]);
			}
		}
	}*/
}

