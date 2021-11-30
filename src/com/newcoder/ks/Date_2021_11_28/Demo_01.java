package com.newcoder.ks.Date_2021_11_28;

import java.util.Scanner;

/**
 * 小组中每位都有一张卡片，卡片上是6位内的正整数，将卡片连起来可以组成多种，计算组成最大数字。
 * @query 处理:
 * @inDesc ","以逗号分割的多个正整数字符串，不需要考虑非数字异常情况，小组最多25人。
 * @outDesc 最大的数字字符串
 * @date 2021/11/28
 *
 * @descIndex 示例 1
 * @descIn (
    22,221
 * )
 * @descOut (
    22221
 * )
 *
 * @descIndex 示例 2
 * @descIn (
        4589,101,41425,9999
 * )
 * @descOut (
        9999458941425101
 * )
 */
public class Demo_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine().replaceAll(" ", "").trim();
            String[] strNums = str.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strNums.length; i++) {
                sb.append(getMaxString(strNums));
            }
            System.out.println(sb);
        }
    }

    public static String getMaxString(String[] strNums) {
        int strLen = 0;
        int maxFirstNum = 0;
        String maxFirstStr = "";
        for (int i = 0; i < strNums.length; i++) {
            String strNum = strNums[i];
            if (strNum == null) {
                continue;
            }
            int num = strNum.charAt(0);

            if (maxFirstNum > num) {
                continue;
            } else if (maxFirstNum == num) {
                String str1 = maxFirstStr;
                String str2 = strNum;
                if (str1.length() > str2.length()) {
                    str1 = strNum;
                    str2 = maxFirstStr;
                }
                boolean b = true;
                for (int j = 1; j < str1.length(); j++) {
                    char c1 = str1.charAt(j);
                    char c2 = str2.charAt(j);
                    if (c1 != c2) {
                        b = c1 > c2;
                        break;
                    }
                }
                if (b) {
                    continue;
                }
            }
            strLen = i;
            maxFirstNum = num;
            maxFirstStr = strNum;
        }
        strNums[strLen] = null;
        return maxFirstStr;
    }
}
