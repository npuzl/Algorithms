package summer;


public class test {
    static int count = 0;

    public static void fun1() {
        System.out.println(count);
        count++;
        fun2();
    }

    public static void fun2() {
        System.out.println(count);
        count++;
        fun1();
    }

    public static void main(String[] args) {

        double[][] arrangers = new double[][]
                {new double[]{4, 1, 4, 8}, new double[]{1, 1, 3, 2},
                new double[]{2, 2, 5, 1}, new double[]{2, 2, 1, 4}};

        test.fun1();
    }
}
