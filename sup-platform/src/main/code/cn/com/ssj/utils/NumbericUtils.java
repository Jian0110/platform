package cn.com.ssj.utils;

public class NumbericUtils {
    public static void main(String[] args) {
        System.out.println(NumbericUtils.isNumber(""));
    }


    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public static String toChineseLowerCase(int numeric) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        return toChinese(numeric,s1,s2);
    }


    public static String toChineseUpperCase(int numeric) {
        String[] s1 = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[] s2 = { "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };
        return toChinese(numeric,s1,s2);
    }

    private static String toChinese(int numeric,String[] s1,String[] s2){
        String string = Integer.toString(numeric);
        StringBuilder result = new StringBuilder();
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result.append(s1[num]).append(s2[n - 2 - i]);
            } else {
                result.append(s1[num]);
            }
        }
        return result.toString();
    }


}
