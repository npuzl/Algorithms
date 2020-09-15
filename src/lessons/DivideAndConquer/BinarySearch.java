package lessons.DivideAndConquer;

/**
 * @date 2020/09/15
 * @author zl
 */

public class BinarySearch {

    /**
     * 主要实现方法
     * 二分查找，很简单。
     * 时间复杂度 O(log_2 n)
     * @return 所查找的元素的索引
     * @param list 所查找的数组
     * @param x 所查找的值
     */
    public static int binarySearch(int[] list,int x){

        int low=0,high=list.length-1;
        int mid;
        while(low!=high){
            mid=(low+high)/2;
            if(list[mid]==x){
                return mid;
            }
            if(list[mid]>x){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] list=new int[]{0,1,3,5,9,51,51,55,61,66};
        //System.out.println(list.length);
        int x=6;
        System.out.println(binarySearch(list,x));

    }
}
