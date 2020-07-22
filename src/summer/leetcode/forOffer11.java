package summer.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class forOffer11 {
    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n=SC.nextInt();
        int[] nums=new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = SC.nextInt();
        }
        System.out.println(minArray(nums));
    }
    public static int minArray(int[] numbers){
        int low=0,high = numbers.length-1;
        while(low<high) {
            int mid=(low+high)/2;
            if (numbers[high] > numbers[mid]) {
                high = mid;
            }else if (numbers[high] <numbers[mid]){
                low = mid+1;
            }else{
                high--;
            }

        }
        return numbers[low];
    }
}
