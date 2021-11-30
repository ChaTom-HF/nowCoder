package com.newcoder.ks.Date_2021_11_28;

import java.util.Scanner;

/**
 * 现有一字符串仅由 '(',')','{','}','[',']' 六种括号组成。
 * 若字符串满足以下条件之一，则为无效字符串：
 * 1.任一类型的左右括号数量不相等
 * 2.存在未按正确顺序（先左后右）闭合的括号。
 * 输出括号的最大嵌套深度，若字符串无效则输出0。
 * 0<=字符串长度<=100000
 * @query 处理:
 * @inDesc 一个只包括 '(',')','{','}','[',']' 的字符串
 * @outDesc 一个整数，最大的括号深度
 * @date 2021/11/28
 *
 * @descIndex 示例 1
 * @descIn (
        []
 * )
 * @descOut (
        1
 * )
 *
 * @descIndex 示例 2
 * @descIn (
        ([]{()})
 * )
 * @descOut (
        3
 * )
 *
 * @descIndex 示例 3
 * @descIn (
        (]
 * )
 * @descOut (
        0
 * )
 *
 * @descIndex 示例 4
 * @descIn (
        ([)]
 * )
 * @descOut (
        0
 * )
 *
 * @descIndex 示例 5
 * @descIn (
        )(
 * )
 * @descOut (
        0
 * )
 *
 * @descIndex 示例 6
 * @descIn (
        ([{[()]}{()}])
 * )
 * @descOut (
        5
 * )
 */
public class Demo_02 {
    static String left = "{[(";
    static String right = "}])";
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//            String str = sc.nextLine().trim();
//            System.out.println(getCount(str));
//        }
    }

    public static int getTotal(String str, boolean isInit) {
        int length = str.length();
        if (length % 2 > 0) {
            return -1;
        }
        int total = 0;
        int index = left.indexOf(str.charAt(0));
        // 校验是否为无效类型
        if (index == -1) {
            return -1;
        }
        char lastChar = str.charAt(str.length() - 1);
        // 检查是否整体被大对号包裹
        if (isInit && lastChar != right.charAt(index)) {
            return -1;
        }
        // 当前层次自增
        total++;

        // 获取内容块
        String[] context = getContext(str.substring(1, str.length() - 2));
        if (context == null) {
            return -1;
        }
        // 获取最大的内容层级
        int maxTotal = 0;
        for (String text: context) {
            int textTotal = getTotal(text, false);
            if (maxTotal < textTotal) {
                maxTotal = textTotal;
            }
        }
        // 层级叠加
        total += maxTotal;
        return total;
    }

    private static String[] getContext(String str) {
        StringBuilder sb = new StringBuilder();
        int noIs = 0;
        char[] chars = str.toCharArray();
        char firstChar = chars[0];
        int index = left.indexOf(firstChar);
        if (index == -1) {
            return null;
        }
        char lastChar = right.charAt(index);

        for (int i = 1; i < chars.length; i++) {

        }
        return sb.toString().split(",");
    }

    public static int getCount(String str) {
        int length = str.length();
        if (length % 2 > 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index;
        // TODO 存在问题，不应前后对检
        int si = 0;
        int ei = chars.length - 1;
        int outEi;
        int count = 0;
        int maxCount = 0;
        while (si < ei) {
            index = left.indexOf(chars[si]);
            // 不属于该类型的
            if (index == -1) {
                return 0;
            }
            // 非结构主体
            if (right.charAt(index) != chars[ei]) {
                if (si == 0) {
                    return 0;
                }
                outEi = ei--;
                int[] r = getRight(si, ei, right.charAt(index), chars);
                System.out.println("[getCount] => r[0]:" + r[0] + ", r[1]:" +  r[1]);
                if (r[0] == -1) {
                    return 0;
                }
                if (maxCount < r[1]) {
                    maxCount = r[1];
                }
                si = ei + 1;
                ei = outEi;
            }
            count++;
            System.out.println("[getCount] => count:" + count);
            if (++si >= ei--) {
                break;
            }
        }
        return count + maxCount;
    }

    private static int[] getRight(int si, int ei, char ec, char[] chars) {
        int count = 0;
        for (int i = si + 1; i < ei;) {
            if (chars[i] == ec) {
                return new int[]{i, count + 1};
            } else {
                int[] r = getRight(i, ei, right.charAt(left.indexOf(chars[i])), chars);
                System.out.println("[getRight] => r[0]:" + r[0] + ", r[1]:" +  r[1]);
                if (r[0] == -1) {
                    return new int[]{-1, 0};
                }
                i = r[0] + 1;
                count += r[1];
            }
        }
        return new int[]{-1, 0};
    }
}
