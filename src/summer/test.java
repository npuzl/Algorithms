package summer;


public class test{
    static int count=0;
    public static void fun1(){
        System.out.println(count);
        count++;
        fun2();
    }
    public static void fun2(){
        System.out.println(count);
        count++;
        fun1();
    }
    public static void main(String[] args){
        test.fun1();
    }
}