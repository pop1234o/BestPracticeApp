package com.liyafeng.view.other.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class ThirdQQ {


  /*  private static final String APP_ID = "1106970798";
    private Tencent mTencent;
    private BaseUiListener iUiListener = new BaseUiListener();
    private String logo_url = "https://oi5yydd1s.qnssl.com/38c7fd61-1ee0-409a-9e68-02064862fb0e.png";

    private ThirdQQ() {
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
// 其中APP_ID是分配给第三方应用的appid，类型为String。
        try {
            mTencent = Tencent.createInstance(APP_ID, BaoGuanApplication.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ThirdQQ getInstance() {
        return new ThirdQQ();
    }

    public void shareUrl(Activity activity, String title, String desc, String url) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, logo_url);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "保观");

        if (mTencent != null) {
            mTencent.shareToQQ(activity, params, iUiListener);

        }
    }


    public void shareImage(Activity activity, String imgUrl) {
        Bundle params = new Bundle();

        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "保观");
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.
                SHARE_TO_QQ_TYPE_IMAGE);
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.
//                SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        if (mTencent != null) {
            mTencent.shareToQQ(activity, params, iUiListener);
        }
    }


    //====================================

    public void shareUrlQZone(Activity activity, String title, String desc, String url) {


        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);

        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, logo_url);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "保观");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);

        if (mTencent != null) {
            mTencent.shareToQQ(activity, params, iUiListener);
        }
    }


    public void shareImgQzone(Activity activity, String imgUrl) {
        Bundle params = new Bundle();

        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imgUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "保观");
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.
                SHARE_TO_QQ_TYPE_IMAGE);
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.
                SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        if (mTencent != null) {
            mTencent.shareToQQ(activity, params, iUiListener);
        }
    }


    public void onResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, iUiListener);
            }
        }

    }

    public static class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {

            LogUtil.i("test", "分享完成");
        }

        @Override
        public void onError(UiError e) {
            String s = "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail;
            LogUtil.i("test", "qq error " + s);
        }

        @Override
        public void onCancel() {
            LogUtil.i("test", "qq cancel");
        }
    }

*/
}

