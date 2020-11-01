package Homework.homework2;

import java.util.Arrays;

public class ComponentPurchase {
    public int[] out = null;
    public int minW = 0;

    ComponentPurchase() {
    }

    public void calculate(int n, int m, int cost, int[][] w, int[][] c) {


    }


    public static void testCase() {
        ComponentPurchase componentPurchase = new ComponentPurchase();
        int n = 3, m = 3, cost = 7;
        int[][] w = new int[][]{new int[]{1, 2, 3}, new int[]{3, 2, 1}, new int[]{2, 3, 2}};
        int[][] c = new int[][]{new int[]{1, 2, 3}, new int[]{5, 4, 2}, new int[]{2, 1, 2}};
        componentPurchase.calculate(n, m, cost, w, c);
        System.out.println(Arrays.toString(componentPurchase.out));
        System.out.println(componentPurchase.minW);
        assert Arrays.equals(componentPurchase.out, new int[]{1, 2, 3});
        assert componentPurchase.minW == 5;
    }

    public static void main(String[] args) {
        ComponentPurchase.testCase();
    }
}
