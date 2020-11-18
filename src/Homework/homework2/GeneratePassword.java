package Homework.homework2;

import java.util.Scanner;

/**
 * 这道题是排列树,一开始走了一点点点弯路，这道题比上一道题简单很多，没啥要判别的
 *
 * @author zl
 * @version 1.0
 * @date 2020/11/08
 */
public class GeneratePassword {
    int n = 0;
    int length = 5;
    String str;
    char[] currentSol;
    char[] bestSol;
    int currentV = 0;

    GeneratePassword(int n, String str) {
        this.n = n;
        this.str = str;
        currentSol = new char[str.length()];
        bestSol=new char[length];
        //currentSol 初始化里面放的每个字符
        for (int i = 0; i < str.length(); i++) {
            currentSol[i] = str.charAt(i);
        }
        //从0开始
        backtrace(0);
        //这里回溯完了要判定一下，是否bestSol没更新过，如果是，则输出no solution
        if(bestSol[0]=='\u0000'){
            System.out.print("no solution");
        }
    }

    private void backtrace(int i) {
        if (i >= length) {
            //在到达叶节点在确定是否是个解
            if (currentV == n) {
                System.arraycopy(currentSol,0,bestSol,0,5);
                //bestSol = currentSol.clone();
                //System.out.println(bestSol);
            }
        } else {
            for (int j = i; j < currentSol.length; j++) {
                //交换
                swap(i, j);
                currentV += Math.pow(-1, i) * Math.pow(getNo(currentSol[i]), i + 1);
                backtrace(i + 1);
                currentV -= Math.pow(-1, i) * Math.pow(getNo(currentSol[i]), i + 1);
                swap(i, j);
            }

        }

    }

    private void swap(int i, int j) {
        char temp = currentSol[i];
        currentSol[i] = currentSol[j];
        currentSol[j] = temp;
    }
    public char[] getBestSol(){
        return bestSol;
    }
    private int getNo(char c) {
        return c - 'A' + 1;
    }

    public static void testcase() {
        Scanner sc = new Scanner(System.in);
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.startsWith("0")) {
                break;
            } else {
                int n = Integer.parseInt(s.split(" ")[0]);
                String str = s.split(" ")[1];
                System.out.println(new GeneratePassword(n, str).getBestSol());
            }
        }
    }

    public static void funTest() {
        int n = 1234567;
        String str = "THEQUICKFROG";
        System.out.println(new GeneratePassword(n, str).getBestSol());
    }

    public static void main(String[] args) {
        GeneratePassword.funTest();
        //GeneratePassword.testcase();
    }

}
