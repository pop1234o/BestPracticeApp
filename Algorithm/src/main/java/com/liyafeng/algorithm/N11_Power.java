package com.liyafeng.algorithm;



public class N11_Power {

    /**
     * 求一个double类型的指数，不考虑大数
     * <p>
     * 最大int 再加1 ，值为最小int
     * 因为最大int 是 011111111 ，加1 变成1000000 这就是最小负数了
     * <p>
     * 反正他们就是32位，加来加去都是在这循环
     */
    public static void main(String[] args) {
//        double power = power(Integer.MAX_VALUE, 2);


        double power = power2(5.5, 3);
        System.out.println(power);

    }

    public static double power(double base, int exponent) {

        if (base == 0 && exponent < 0) {
            return 0;
        }

        int absExponent = exponent < 0 ? -exponent : exponent;
        double result = powerWithUnsignedExponent(base, absExponent);

        if(exponent<0){
            result /=1;
        }

        return result;

    }

    private static double powerWithUnsignedExponent(double base, int absExponent) {
        double result = 1;
        for (int i = 0; i < absExponent; i++) {
            result *= base;
        }
        return result;
    }


    /**
     * 递归写法
     * 斐波那契数列
     * @param base
     * @param exponent
     * @return
     */
    public static double power2(double base, int exponent){

        if(exponent==1){
            return base;
        }
        if(exponent==0){
            return 1;
        }

        double result = power2(base, exponent >> 1);

        result *= result;
        if(exponent%2==1){//奇数
            result*=base;
        }

        return result;
    }

}
