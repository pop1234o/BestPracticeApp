package com.liyafeng.view.other.textview;

public class Main {

    /**
     * https://www.cnblogs.com/tianzhijiexian/p/4222393.html
     * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0120/2335.html
     *
     * Spannable
     *
     * SpannableString msp = new SpannableString("测试文字");
     *
     * 设置字体样式（TypefaceSpan）
     * 大小      AbsoluteSizeSpan(20)
     * 相对大小  RelativeSizeSpan(0.5f)
     *
     * ForegroundColorSpan
     * BackgroundColorSpan
     * StyleSpan
     * UnderlineSpan
     * StrikethroughSpan
     * SubscriptSpan 下标和上标
     * SuperscriptSpan
     * URLSpan 跳转url
     * ScaleXSpan
     * IconMarginSpan
     * ImageSpan
     * MaskFilterSpan
     *
     *
     * ===========textview字间距==========
     * android:letterSpacing="0.6"  0开始
     *
     *
     * ==========textview设置点击事件======
     *
     *   ClickableSpan clickableSpan = new ClickableSpan() {
     *             @Override
     *             public void updateDrawState(@NonNull TextPaint ds) {
     *                 super.updateDrawState(ds);
     *                 ds.setColor(getContext().getColor(R.color.color));
     *                 ds.setUnderlineText(false);
     *             }
     *
     *             @Override
     *             public void onClick(@NonNull View widget) {
     *             }
     *         };
     *
     * spannableString.setSpan(clickableSpan, indexOf, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
     * tvPrivacyContent.setMovementMethod(LinkMovementMethod.getInstance());
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
