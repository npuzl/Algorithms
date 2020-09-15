package lessons.DivideAndConquer;

/**
 * @date 2020/09/15
 * @author zl
 */
public class IntegerDivide {
    /**最大为加数n(n<=m)的划分个数为integerDivide(n,m)
     * TODO 这个代码有问题
     */
    public static int integerDivide(int n,int m){
        if (m<1||n<1) {
            return 0;
        }
        if(n==1&&m==1) {
            return 1;
        }
        if(m>n) {
            return integerDivide(n,n);
        }
        if(m==n){
            return 1+integerDivide(n,n-1);
        }
        if(m>1&&m<n){
            return integerDivide(n,m-1)+integerDivide(n-m,m);
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(integerDivide(6,10));
    }
}
