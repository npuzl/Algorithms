import java.util.Arrays;

public class parameterPassingMethod {


    public static void changeBasicdata(int a,int[] b){
        a=3;
        b[0]=5;
    }
    public static void main(String[] args) {
        int a=1;
        int []b=new int[]{2,3,4};
        changeBasicdata(a,b);
        System.out.println(a);//数没有改变
        System.out.println(Arrays.toString(b));//数组改变了
        int []c=b;
        c[0]=6;
        System.out.println(Arrays.toString(c));//c改变了
        System.out.println(Arrays.toString(b));//b也改变了
        System.out.println((a+2)/2);
    }
}
