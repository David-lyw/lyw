package com.lyw.inputfilter;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by David on 16/10/19.
 *  Created by David on 16/10/20.
 * <p>
 * 输入类型：0.00；.00;.01;.03;2.34;等数据
 * 使用方法：
 * EditText.setFilters(new InputFilter[]{new DoubleInputFilter()});
 * <p>
 * <p>
 * <p>
 * 优化：可以写方法设置最大值，和小数点的位数。
 * 缺点:用户如果只输入小数点。
 */
public class DoubleInputFilter implements InputFilter {
    /**
     * 最大数字
     */
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    /**
     * 小数点后的数字的位数
     */
    public static final int PONTINT_LENGTH = 2;
    Pattern p;

    public DoubleInputFilter() {
        p = Pattern.compile("([0-9]|\\.)*");   //除数字外的其他的
    }

    /**
     * source    新输入的字符串
     * start    新输入的字符串起始下标，一般为0
     * end    新输入的字符串终点下标，一般为source长度-1
     * dest    输入之前文本框内容
     * dstart    原内容起始坐标，一般为0
     * dend    原内容终点坐标，一般为dest长度-1
     */

    @Override
    public CharSequence filter(CharSequence src, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String oldtext = dest.toString();
        String newText = src.toString().trim();

        //验证删除等按键
        if ("".equals(newText)) {
            return "";
        }
        //验证非数字或者小数点的情况
        Matcher m = p.matcher(src);
        if (oldtext.contains(".")) {
            //已经存在小数点的情况下，只能输入数字
            if (!m.matches()) {//不匹配
                return "";
            }
        } else {
            //未输入小数点的情况下，可以输入小数点和数字
            if (!m.matches() && !src.equals(".")) {
                return "";
            }
        }
        //验证输入金额的大小
        if (!newText.equals("") && !newText.equals(".")) {
            double dold = Double.parseDouble(oldtext + src.toString());
            if (dold > MAX_VALUE) {
                return dest.subSequence(dstart, dend);
            } else if (dold == MAX_VALUE) {
                if (src.toString().equals(".")) {
                    return dest.subSequence(dstart, dend);
                }
            }
        }
        //验证小数位精度是否正确
        if (oldtext.contains(".")) {
            int index = oldtext.indexOf(".");
            int len = dend - index;
            //小数位只能2位
            if (len > PONTINT_LENGTH) {
                CharSequence newText2 = dest.subSequence(dstart, dend);
                return newText2;
            }
        }



        return dest.subSequence(dstart, dend) + src.toString();
    }
}
