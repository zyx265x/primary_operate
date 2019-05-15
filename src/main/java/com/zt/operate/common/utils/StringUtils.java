package com.zt.operate.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils{

    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 字符串长度格式化，用于避免由于字符串过长破坏页面样式.
     *
     * @param str       原字符串
     * @param maxLength 最大长度
     * @return 格式化后的字符串
     */
    public static String lengthFormat(String str, int maxLength) {
        return str.length() > maxLength ? str.substring(0, maxLength) + "..."
                : str;
    }

    public static boolean isNumeric(String str) {
        if (str == null || isBlank(str)) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        if (isNotBlank(email)) {
            try {
                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            } catch (Exception e) {
                flag = false;
            }
        }

        return flag;
    }




    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        str=toUpperFristChar(str);
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母大写  高效
     * @param string
     * @return
     */
    public static String toUpperFristChar(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }

    public static String toJavaType(String dataType) {
        String javaType = "";
        if ("bigint".equals(dataType)) {
            javaType = "Long";
        } else if ("int".equals(dataType)) {
            javaType = "Integer";
        } else if ("tinyint".equals(dataType)) {
            javaType = "Integer";
        }else if ("datetime".equals(dataType) || "date".equals(dataType)) {
            javaType = "Date";
        } else if ("float".equals(dataType)) {
            javaType = "Float";
        } else if ("varchar".equals(dataType)) {
            javaType = "String";
        }
        return javaType;
    }

    /**
     * 首字母大写
     */
    public static String firstLetter2UpperCase(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }

    /**
     * 首字母小写
     */
    public static String firstLetter2LowerCase(String name) {
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);

    }




    /**
     * 字符串数组是否包含指定的字符串
     */
    public static boolean isIn(String method, String[] arr) {
        if (StringUtils.isNotBlank(method)) {
            for (String str : arr) {
                if (str.equals(method))
                    return true;
            }
        }

        return false;
    }

    /**
     * 字符串转成long数组
     * @param str
     * @return
     */
    public static Long[] str2LongArr(String str) {
        Long[] rtn = null;
        if (isNotBlank(str)) {
            try {
                String[] arr = str.split(",");
                rtn = new Long[arr.length];
                for (int i = 0; i < arr.length; i++) {
                    rtn[i] = Long.valueOf(arr[i]);
                }
            } catch (NumberFormatException e) {
                rtn = null;
                e.printStackTrace();
            }
        }

        return rtn;
    }

    /**
     * 用空格补齐数字
     * @param val 数字
     * @param num 补齐空格数
     * @return
     */
    public static String  fullWithSpace(int val,int num) {
        String temp = "";
        int lenth = String.valueOf(val).length();
        for(int i=0;i<(num-lenth);i++){
            temp += " ";
        }
        String format = temp + "%s";
        String result = String.format(format, val);
        return result;
    }

    public static void main(String[] args) {
        String reg = "(,|，)";
        String t = "abc，bcd,好";
        System.out.println("--------------" + t.replaceAll(reg, "#"));
    }
}
