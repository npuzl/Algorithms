package lessons.DynamicProgramming;

import java.util.Arrays;
import java.util.Random;

/**
 * TODO 这个问题如果要考虑完备了挺麻烦，就先不写了，有时间再写
 * @date 2020/10/08
 * @author zl
 */
public class Investment {
    public static double investment(double money,int projects,double []values,int []decide){

        return 0;
    }
    public static void main(String[] args) {
        Random random = new Random();
        int n=10;//项目数
        double m=1500;//总钱数
        double []values=new double[n];//存储每个项目要花多少钱
        double sum=0;
        int []decide = new int[n];//决策第i个是否投资，1投资，0不投资
        //生成每个项目要花的钱数
        for (int i = 0; i < n; i++) {
            values[i] = random.nextDouble()*m/(Math.sqrt(n));
            sum+=values[i];
        }
        System.out.println(Arrays.toString(values));
        System.out.println(investment(m,n,values,decide));
        System.out.print("投资第 ");
        for (int i = 0; i < n; i++) {
            if(decide[i] ==1)
                System.out.print(i+1+" ");
        }
        System.out.println("个项目");

    }
}
