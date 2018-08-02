package com.liyafeng.view.other.share;

//import com.qusukj.baoguan.BaoGuanApplication;
//import com.qusukj.baoguan.R;
//import com.qusukj.baoguan.util.LogUtil;
//import com.sina.weibo.sdk.WbSdk;
//import com.sina.weibo.sdk.api.ImageObject;
//import com.sina.weibo.sdk.api.TextObject;
//import com.sina.weibo.sdk.api.WebpageObject;
//import com.sina.weibo.sdk.api.WeiboMultiMessage;
//import com.sina.weibo.sdk.auth.AuthInfo;
//import com.sina.weibo.sdk.share.WbShareCallback;
//import com.sina.weibo.sdk.share.WbShareHandler;
//import com.sina.weibo.sdk.utils.Utility;

public class ThirdSina {
}

//public class ThirdSina implements WbShareCallback {
//
//    public static final String KEY_SHARE_TYPE = "baoguan_share";
//    public static final String App_Key = "2710340573";
//    //    public static final String App_Secret = " 3f561dd0c17184c85279b5bafe21d145";
//    public static final int SHARE_CLIENT = 1;
//    public static final int KEY_ALL_IN_ONE = 2;
//    private static ThirdSina thirdSina;
//
//    private final WbShareHandler shareHandler;
//    private final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
//    private final String SCOPE = "";
//
//    public static ThirdSina getInstance(Activity activity) {
//        if (thirdSina == null) {
//            thirdSina = new ThirdSina(activity);
//        }
//        return thirdSina;
//    }
//
//    private ThirdSina(Activity activity) {
//        Context context = BaoGuanApplication.getContext();
//        WbSdk.install(context, new AuthInfo(context, App_Key, REDIRECT_URL, SCOPE));
//        shareHandler = new WbShareHandler(activity);
//        shareHandler.registerApp();
//    }
//
//
//    public void sharePhoto(Bitmap bitmap) {
//
//        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
//
//        ImageObject imageObject = new ImageObject();
//        imageObject.setImageObject(bitmap);
//
//        weiboMessage.imageObject = imageObject;
//
//        shareHandler.shareMessage(weiboMessage, false);
//
//    }
//
//
//    public void shareUrl(String url, String title, String description) {
//        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
//
//        WebpageObject mediaObject = new WebpageObject();
//        mediaObject.identify = Utility.generateGUID();
//        mediaObject.title = title;
//        mediaObject.description = description;
//        Bitmap bitmap = BitmapFactory.decodeResource(BaoGuanApplication.getContext().getResources(), R.mipmap.ic_launcher);
//        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
//        mediaObject.setThumbImage(bitmap);
//        mediaObject.actionUrl = url;
//        mediaObject.defaultText = "保观";
//
//
//        //不写这个总是分享失败（客户端分享）
//        weiboMessage.mediaObject = mediaObject;
//        TextObject textObject = new TextObject();
//        textObject.text = title;
//        weiboMessage.textObject = textObject;
//        shareHandler.shareMessage(weiboMessage, false);
//
//    }
//
//    @Override
//    public void onWbShareSuccess() {
//
//        LogUtil.i("test", "分享成功");
//    }
//
//    @Override
//    public void onWbShareCancel() {
//        LogUtil.i("test", "分享取消");
//    }
//
//    @Override
//    public void onWbShareFail() {
//        LogUtil.i("test", "分享失败");
//    }
//
//
//    public void onNewIntent(Intent intent) {
//        shareHandler.doResultIntent(intent, this);
//    }
//}
