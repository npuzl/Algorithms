package Homework.homework2;

import java.util.Arrays;

public class ComponentPurchase {
    public int[] out = null;
    public int minW = Integer.MAX_VALUE;
    public int currentCost=-1;
    public int []currentSol;
    int n;
    int m;
    int cost;
    int [][]w;
    int [][]c;


    ComponentPurchase(int n, int m, int cost, int[][] w, int[][] c) {
        this.n=n;
        this.m=m;
        this.cost=cost;
        this.w=w;
        this.c=c;
    }

    public void calculate(int i) {
        if(i>n){
            minW=currentCost;
            out=currentSol;
        }else{
            for(int j=i;j<=n;j++){
                if(check()){
                    swap(i,j);

                    if(true){
                        // TODO
                    }
                    swap(i,j);
                }
            }
        }
    }

    private boolean check(){
        return true;
    }

    private void swap(int i,int j){
        int temp = currentSol[i];
        currentSol[i]=currentSol[j];
        currentSol[j]=temp;
    }


    public static void testCase() {
        int n = 3, m = 3, cost = 7;
        int[][] w = new int[][]{new int[]{1, 2, 3}, new int[]{3, 2, 1}, new int[]{2, 3, 2}};
        int[][] c = new int[][]{new int[]{1, 2, 3}, new int[]{5, 4, 2}, new int[]{2, 1, 2}};
        ComponentPurchase componentPurchase = new ComponentPurchase(n, m, cost, w, c);
        componentPurchase.calculate(1);
        System.out.println(Arrays.toString(componentPurchase.out));
        System.out.println(componentPurchase.minW);
        assert Arrays.equals(componentPurchase.out, new int[]{1, 2, 3});
        assert componentPurchase.minW == 5;
    }

    public static void main(String[] args) {
        ComponentPurchase.testCase();
    }
}
