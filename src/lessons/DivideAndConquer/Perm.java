package lessons.DivideAndConquer;



/**
 * 用于实现一个列表的全排列，list为待排列数组，k为全排列起始元素索引，m为结束元素索引
 * @date 2020/09/15
 * @author zl
 */
public class Perm {

    /**
     * 用于记录全排列数
     */
    public static int count=0;

    /** 主要实现函数
     *
     *  20200915 我还是不太懂这个代码
     */
     public static void perm(int[] list,int k, int m){
        if(k==m){
            for(int i=0;i<m;i++){
                System.out.print(list[i]);

            }
            System.out.println();
            count++;
        }else{
            for(int i=k;i<m;i++){

                int temp=list[k];
                list[k]=list[i];
                list[i]=temp;

                perm(list,k+1,m);

                temp=list[k];
                list[k]=list[i];
                list[i]=temp;
            }
        }
    }
    public static void main(String[] args) {
        int[] list=new int[]{1,2,3,4,5};
        int k=0,m=list.length;
        perm(list,k,m);
        System.out.println(count);

    }
}
