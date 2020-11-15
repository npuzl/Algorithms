package lessons.DivideAndConquer;

/**选择第k大(小)的元素
 * 时间复杂度O(n)
 * @author zl
 * @date 2020/09/17
 * @version 1.0
 */
public class kthSelect {

    public static int kthSelectLargest(int []list,int k){
        if(k>list.length){
            return -1;
        }
        int left=0,right = list.length-1;
        while (left < right){
            int mid=(left + right)/2;
            if(k>(mid-left+1)){ //3 4大
                left=mid;
            }else {
                right=mid;
            }

        }
        return 0;
    }

    public static void largestHelper(int[] list){

    }
    public static int kthSelectSmallest(int []list,int k){
        return 0;
    }
    public static void main(String[] args) {
        int []list = new int[]{1,2,3,4,5,6,7,8,10,};
        System.out.println(kthSelectLargest(list,3));
        System.out.println(kthSelectSmallest(list, 3));
    }
}
