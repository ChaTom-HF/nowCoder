package com.newcoder.string;

import java.util.*;

public class StrToOutAndLen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            List<String> list = new ArrayList<>();
            int len = conutinumax(list, str);
            for (String item: list) {
                System.out.print(item);
            }
            System.out.println("," + len);
        }
    }

    private static int conutinumax(List<String> list, String str) {
        int max = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                while (i < length && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    stringBuilder.append(str.charAt(i++));
                }
                max = Math.max(max, stringBuilder.length());
                list.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() < max) {
                list.remove(i);
                i--;
            }
        }
        return max;
    }
}
