package Homework.homework2;

class HighPrecision {
    int IntNum = 50;
    int DecNum = 50;
    int[] Int = new int[IntNum];
    int[] Dec = new int[DecNum];
    int DecUse;
    int IntUse;
    HighPrecision(String str)throws NumberFormatException {
        int l = str.length();
        int dotIndex=0;
        for (int i = 0; i < l; i++) {
            char c = str.charAt(i);
            if (!((c <= '9' && c >= '0') || c == '.')) {
                throw new NumberFormatException();
            }
            if (c == '.')
                dotIndex = i;
        }
        if (dotIndex > IntNum) {
            System.out.println(
                    "The length of input num is longer than the inner num length, try to add inner num length");
        }
        IntUse=0;
        for (int i = dotIndex - 1; i >= 0; i--) {
            Int[IntUse++] = str.charAt(i) - '0' + 1;
        }
        DecUse = 0;
        for (int i = dotIndex + 1; i < str.length(); i++) {
            Dec[DecUse++] = str.charAt(i) - '0' + 1;
        }
        /* 例如，输入123456789987.16515664889449
         * 则Int里面保存[7,8,9,9,8,7,6,5,4,3,2,1]
         * Dec里面保存[1,6,5,1,5,6,6,4,8,8,9,4,4,9]
         *
         */
    }

    public String multiply(HighPrecision num2) {
        return null;
    }

    public String minus(HighPrecision num2) {
        return null;
    }

    public String add(HighPrecision num2) {
        return null;
    }

    public String divide(HighPrecision num2) {
        return null;
    }

    public static void testcase() {
        HighPrecision num1 = new HighPrecision("1234567898765443231231231231.1231231231312313");
        HighPrecision num2 = new HighPrecision("123454343554667547523423465.55778909987654345678");
        System.out.println(num1.multiply(num2));
        System.out.println(num1.add(num2));
        System.out.println(num1.minus(num2));
        System.out.println(num1.divide(num2));
    }

    public static void main(String[] args) {
    HighPrecision.testcase();
    }
}