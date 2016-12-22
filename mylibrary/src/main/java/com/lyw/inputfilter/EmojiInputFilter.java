package com.lyw.inputfilter;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by David on 16/11/22.
 * 正则虽然红色,但能正常运行。()
 * 输入框不能输入emoji。
 */
public class EmojiInputFilter implements InputFilter {
    Pattern p;

    public EmojiInputFilter() {
        // p = Pattern.compile("/[\u1F60-\u1F64]|[\u2702-\u27B0]|[\u1F68-\u1F6C]|[\u1F30-\u1F70]|[\u2600-\u26ff]/g");
        p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        // p = Pattern.compile("[\\\\ud800\\\\udc00-\\\\udbff\\\\udfff\\\\ud800-\\\\udfff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        // p = Pattern.compile("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher emojiMatcher = p.matcher(source);
        if (emojiMatcher.find()) {
            return "";
        }
        return null;
    }
}
