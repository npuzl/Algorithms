package Homework.homework2;


/**
 * 这是子集树，每一位都有0-9十个选择
 *
 * @author zl
 * @version 1.0
 * @date 2020/11/9
 */
public class InterestingNumber {
    int[] currentNum;
    int[] bestNum;
    int length = 0;
    int currentLength = 0;

    /**
     * length为检测的位数
     * @param length
     */
    InterestingNumber(int length) {
        currentNum = new int[length + 1];
        bestNum = new int[length + 1];
        this.length = length;
        backtrace(1);
    }

    private void backtrace(int i) {
        if (i >= length) {
            return;
        } else {
            for (currentNum[i] = 0; currentNum[i] <= 9; currentNum[i]++) {
                if (check(i)) {
                    if (bigger(currentNum, bestNum, i)) {
                        bestNum = currentNum.clone();
                        currentLength = i;
                    }
                    backtrace(i + 1);
                }
            }
        }
    }

    /**
     * 查看第i个能不能放，用的取余数
     * @param i
     * @return
     */
    private boolean check(int i) {
        if (currentNum[1] == 0) return false;
        int t = 0;
        for (int j = 1; j <= i; j++) {
            t = t * 10 + currentNum[j];
            t = t % i;
        }
        t = t % i;
        if (t == 0) return true;
        return false;
    }

    /**
     * 比较是现在算出的大还是已有的最大值大
     * @param current
     * @param best
     * @param i
     * @return
     */
    private boolean bigger(int[] current, int[] best, int i) {
        if (i > currentLength) return true;
        if (i < currentLength) return false;
        for (int j = i; j >= 1; j--) {
            if (current[j] > best[j])
                return true;
            else if (current[j] < best[j])
                return false;
        }
        return false;
    }

    /**
     * 最终算到的最大值
     * @return
     */
    public String getMAX() {
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=currentLength;i++){
            sb.append(bestNum[i]);
        }
        return sb.toString();
    }

    public static void testcase() {

        new InterestingNumber(99);
        System.out.println(new InterestingNumber(101).getMAX());
    }

    public static void main(String[] args) {
        InterestingNumber.testcase();
    }
}
