package com.indusfo.spc.sdk.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * @Descirption
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/30 13:31
 */
public class NumberUtils {

    private static final Double MILLION = 10000.0;

    private static final Double MILLIONS = 1000000.0;
        private static final Double BILLION = 100000000.0;
        private static final String MILLION_UNIT = "万";
        private static final String BILLION_UNIT = "亿";

        public static final int DEFAULT_VALUE = 0;
        public static final DecimalFormat df2 = new DecimalFormat("#0.00");

        public static Number sum(Number... ns){
            if (ns == null || ns.length == 0)
                return 0;

            double d = 0;
            for (Number number : ns) {
                if (ns != null) {
                    d += number.doubleValue();
                }
            }

            return d;
        }

        public static int sum(int... ns){
            if (ns == null || ns.length == 0)
                return 0;

            int d = 0;
            for (int number : ns) {
                if (ns != null) {
                    d += number;
                }
            }

            return d;
        }

        /**
         * 判断该数字是否为有效数字
         * 主要判断 是否为空和是否大于DEFAULT_VALUE
         *
         * @param n
         * @return true 为有效数字
         */
        public static boolean isValid(Number n){
            if (n != null) {
                if (n instanceof Double) {
                    return n.doubleValue() > 0D;
                } else if (n instanceof Float) {
                    return n.floatValue() > 0L;
                } else if (n instanceof Long) {
                    return n.longValue() > 0L;
                }

                return n.intValue() > DEFAULT_VALUE;
            } else {
                return false;
            }
        }

        /**
         * 判断该数字是否为无效数字
         *
         * @param n
         * @return true为无效数字
         */
        public static boolean isNotValid(Number n){
            return !isValid(n);
        }

        /**
         * 判断该数字是否为无效数字
         *
         * @param n
         * @return true为无效数字
         */
        public static boolean isGt0Valid(Number n){
            return (n != null && n.intValue() >= DEFAULT_VALUE);
        }

        /**
         * 去高位数保留指定长度的整数
         * cutTopNumber(7123456, 3) -->456
         *
         * @param n
         *            目标数
         * @param len
         *            保留的长度
         * @return
         */
        public static Integer cutTopNumber(Integer n,int len){
            if (n == null)
                return -1;
            String ns = String.valueOf(n);

            if (ns.length() <= len)
                return n;

            return Integer.parseInt(ns.substring(ns.length() - len,ns.length()));
        }

        /**
         * 对集合里面的数做 逻辑（|）的操作并返回
         *
         * @param ns
         *            Number...集合
         * @return Number
         */
        public static Number logic(Number... ns){
            Number n = 0;
            for (Number number : ns) {
                n = n.intValue() | number.intValue();
            }
            return n;
        }

        /**
         * 对集合里面的数做 逻辑（|）的操作并返回
         *
         * @param nl
         *            List<Number>集合
         * @return Number
         */
        public static Number logic(List<? extends Number> nl){
            Number n = 0;
            for (Number number : nl) {
                n = n.intValue() | number.intValue();
            }
            return n;
        }

        /**
         *
         * t （逻辑 |）是否在 t2 里面
         *
         * @param t
         * @param t2
         * @return
         */
        public static boolean logicIsContain(Integer t,Integer t2){
            if (t == null)
                return false;
            if (t < 0 || t2 < 0)
                return false;
            return (t & t2) > 0;
        }

        public static Number random(){
            return random(6);
        }



        public static Number random(int c){
            Random r = new Random();
            double nextDouble = r.nextDouble();
            while (nextDouble < 0.1) {
                nextDouble = r.nextDouble();
            }

            double pow = Math.pow(10,c);
            int rannum = (int) (nextDouble * pow); // 获取随机数
            return rannum;
        }

        public static double setDecimal(double d,int len){
            double rate = Math.pow(10,len);
            return ((long) (d * rate)) / rate;
        }

        public static long parseLong(String str,long defaultValue){
            try {
                return Long.parseLong(str);
            } catch (Exception e) {
                return defaultValue;
            }
        }

        public static long parseLong(String str){
            return parseLong(str,-1);
        }

        public static int parseInt(String str,int defaultInt){
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                return defaultInt;
            }
        }

        public static int parseInt(String str){
            return parseInt(str,-1);
        }

        public static double parseDouble(String str,double defaultDouble){
            try {
                return Double.parseDouble(str);
            } catch (Exception e) {
                return defaultDouble;
            }
        }

        public static double parseDouble(String str){
            return parseDouble(str,0d);
        }

        public static float parseFloat(String str,float defaultFloat){
            try {
                return Float.parseFloat(str);
            } catch (Exception e) {
                return defaultFloat;
            }
        }

        public static float parseFloat(String str){
            return parseFloat(str,0F);
        }

        public static final String format(Number n){
            if (n instanceof Double) {
                if (n.doubleValue() > 0D) {
                    return "+" + n.doubleValue();
                }
                if (n.doubleValue() < 0D) {
                    return "-"+String.valueOf( Math.abs(n.doubleValue()));
                }
            }
            return "0";
        }

        public static String amountConversion(double amount){
            // 最终返回的结果值
            String result = String.valueOf(amount);
            // 四舍五入后的值
            double value = 0;
            // 转换后的值
            double tempValue = 0;
            // 余数
            double remainder = 0;

            // 金额大于1百万小于1亿
            if (amount > MILLIONS && amount < BILLION) {
                tempValue = amount / MILLION;
                remainder = amount % MILLION;

                // 余数小于5000则不进行四舍五入
			/*if (remainder < (MILLION / 2)) {
				value = formatNumber(tempValue,2,true);
			} else {

			}*/
                value = formatNumber(tempValue,2,true);
                // 如果值刚好是10000万，则要变成1亿
                if (value == MILLION) {
                    result = zeroFill(value / MILLION) + BILLION_UNIT;
                } else {
                    result = zeroFill(value) + MILLION_UNIT;
                }
            }
            // 金额大于1亿
            else if (amount > BILLION) {
                tempValue = amount / BILLION;
                remainder = amount % BILLION;

                // 余数小于50000000则不进行四舍五入
                if (remainder < (BILLION / 2)) {
                    value = formatNumber(tempValue,2,false);
                } else {
                    value = formatNumber(tempValue,2,true);
                }
                result = zeroFill(value) + BILLION_UNIT;
            } else {
                result = zeroFill(amount);
            }
            return result;
        }

        /**
         * 对数字进行四舍五入，保留2位小数
         *
         * @author
         * @version 1.00.00
         *
         * @date 2018年1月18日
         * @param number
         *            要四舍五入的数字
         * @param decimal
         *            保留的小数点数
         * @param rounding
         *            是否四舍五入
         * @return
         */
        public static Double formatNumber(double number,int decimal,boolean rounding){
            BigDecimal bigDecimal = new BigDecimal(number);

            if (rounding) {
                return bigDecimal.setScale(decimal, RoundingMode.HALF_UP).doubleValue();
            } else {
                return bigDecimal.setScale(decimal,RoundingMode.DOWN).doubleValue();
            }
        }

        /**
         * 对四舍五入的数据进行补0显示，即显示.00
         *
         * @author
         * @version 1.00.00
         *
         * @date 2018年1月23日
         * @return
         */
        public static String zeroFill(double number){

            String value = removeDoubleZero(number);
            //String value = String.valueOf(number);

            if (value.indexOf(".") < 0) {
                //value = value + ".00";
            } else {
                String decimalValue = value.substring(value.indexOf(".") + 1);

                if (decimalValue.length() < 2) {
                    value = value + "0";
                }
            }
            return value;
        }

        public static String removeDoubleZero(Double num){
            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

            String result = decimalFormat.format(num);

            return result;
        }

    }
