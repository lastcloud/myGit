package com.indusfo.spc.sdk.utils;

/**
 * @Descirption 16进工具类
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/27 02:19
 */
public class HexUtils {



    public static String byte2HexStr(byte[] arrB) throws Exception {

        int iLen = arrB.length;

        // 每个byte用2个字符才能表示，所以字符串的长度是数组长度的2倍

        StringBuffer sb = new StringBuffer(iLen * 2);

        for (int i = 0; i < iLen; i++) {

            int intTmp = arrB[i];

            // 把负数转换为正数

            while (intTmp < 0) {

                intTmp = intTmp + 256;

            }

            // 小于0F的数需要在前面补0

            if (intTmp < 16) {

                sb.append("0");

            }

            sb.append(Integer.toString(intTmp, 16));

        }

        return sb.toString();

    }


    public static String byteHEX(byte ib) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }


    /**将16进制转换为二进制

     * @param hexStr

     * @return

     */

    public static byte[] hexStr2Byte_2(String hexStr) {

        if (hexStr.length() < 1)

            return null;

        byte[] result = new byte[hexStr.length()>>1];

        for (int i = 0;i< hexStr.length()/2; i++) {

            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);

            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);

            result[i] = (byte) (high * 16 + low);

        }

        return result;

    }


    public static byte[] hexStr2Byte(String strIn) throws Exception {

        byte[] arrB = strIn.getBytes();

        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2

        byte[] arrOut = new byte[iLen>>1];

        for (int i = 0; i < iLen; i = i + 2) {

            String strTmp = new String(arrB, i, 2);

            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);

        }

        return arrOut;

    }

}
