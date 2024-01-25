package com.liyafeng.view.custom;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectableTextView extends AppCompatTextView {

    private SpannableString mSpannableString;
    private CharSequence mText;
    private BackgroundColorSpan mSelectedBackSpan;
    private ForegroundColorSpan mSelectedForeSpan;
    private BufferType mBufferType;
    private OnWordClickListener mOnWordClickListener;

    // 是否允许单选
    private boolean isEnableSingleSelect = true;
    // 是否允许多选
    private boolean isEnableMultSelect = false;

    // 选中文字的前景色
    private int mSelectTextFrontColor = Color.WHITE;
    // 选中文字的背景色
    private int mSelectTextBackColor = Color.BLACK;


    public void setEnableMultSelect(boolean enableMultSelect) {
        isEnableMultSelect = enableMultSelect;
    }

    public void setEnableSingleSelect(boolean enableSingleSelect) {
        isEnableSingleSelect = enableSingleSelect;
    }

    /**
     * 设置前景色
     *
     * @param selectTextFrontColor
     */
    public void setSelectTextFrontColor(int selectTextFrontColor) {
        mSelectTextFrontColor = selectTextFrontColor;
    }

    /**
     * 设置前景色
     *
     * @param selectTextFrontColor
     */
    public void setSelectTextFrontColorRes(int selectTextFrontColor) {
        mSelectTextFrontColor = getContext().getResources().getColor(selectTextFrontColor);
    }


    /**
     * 设置背景色
     *
     * @param selectTextBackColor
     */
    public void setSelectTextBackColor(int selectTextBackColor) {
        mSelectTextBackColor = selectTextBackColor;
    }

    /**
     * 设置背景色
     *
     * @param selectTextBackColor
     */
    public void setSelectTextBackColorRes(int selectTextBackColor) {
        mSelectTextBackColor = getContext().getResources().getColor(selectTextBackColor);
    }

    public SelectableTextView(Context context) {
        this(context, null);
    }

    public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
        mOnWordClickListener = onWordClickListener;
    }

    public SelectableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (isEnableSingleSelect) {
            // 单个复制
            mText = text;
            mSpannableString = new SpannableString(mText);
            mBufferType = type;
            setMovementMethod(LinkMovementMethod.getInstance());
            // 分割单词
            List<WordInfo> wordInfos = getWordInfo();
            // 设置点击事件
            for (int i = 0; i < wordInfos.size(); i++) {
                WordInfo info = wordInfos.get(i);
                mSpannableString.setSpan(getClickableSpan(), info.getStart(), info.getEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            super.setText(mSpannableString, type);
        }
        if (isEnableMultSelect) {
            setTextIsSelectable(true);
        }
    }


    @Override
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        dismissSelected();
        super.setCustomSelectionActionModeCallback(actionModeCallback);
    }

    public void dismissSelected() {
        mSpannableString.removeSpan(mSelectedBackSpan);
        mSpannableString.removeSpan(mSelectedForeSpan);
        setText(mSpannableString, mBufferType);
    }

    /**
     * 获取单词的info
     *
     * @return
     */
    private List<WordInfo> getWordInfo() {
        // 获取分割之后的所有单词
        List<String> words = splitWord();
        // 创建WordInfos
        List<WordInfo> result = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            // 获取开始位置
            int start = mText.toString().indexOf(word, startIndex);
            int end = start + word.length();
            startIndex = end;
            WordInfo wordInfo = new WordInfo();
            wordInfo.setStart(start);
            wordInfo.setEnd(end);
            result.add(wordInfo);
        }
        return result;
    }

    /**
     * 获取分割之后的所有单词
     *
     * @return
     */
    private List<String> splitWord() {
        if (TextUtils.isEmpty(mText.toString())) {
            return new ArrayList<String>();
        }
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z-’]+");
        Matcher matcher = pattern.matcher(mText);
        while (matcher.find()) {
            results.add(matcher.group(0));
        }
        return results;
    }

    private void setSelectedSpan() {
        if (mSelectedBackSpan == null || mSelectedForeSpan == null) {
            mSelectedBackSpan = new BackgroundColorSpan(mSelectTextBackColor);
            mSelectedForeSpan = new ForegroundColorSpan(mSelectTextFrontColor);
        } else {
            mSpannableString.removeSpan(mSelectedBackSpan);
            mSpannableString.removeSpan(mSelectedForeSpan);
        }
        try {
            mSpannableString.setSpan(mSelectedBackSpan, getSelectionStart(), getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mSpannableString.setSpan(mSelectedForeSpan, getSelectionStart(), getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setText(mSpannableString, mBufferType);
    }

    private ClickableSpan getClickableSpan() {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                String word = "";
                try {
                    word = getText().subSequence(getSelectionStart(), getSelectionEnd()).toString();
                } catch (Exception e) {
                }
                // 高亮选中
                setSelectedSpan();
                // 点击事件
                if (mOnWordClickListener != null && !word.trim().isEmpty()) {
                    mOnWordClickListener.onClick(word);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {

            }
        };
    }
}