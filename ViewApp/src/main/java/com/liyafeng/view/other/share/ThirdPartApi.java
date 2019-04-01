package com.liyafeng.view.other.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * 调用wxapi.sendReq接口，返回true，但微信客户端并未启动，请检查以下几项：
 * A:
 * 1）微信是否安装
 * 2）调用时的Apk包名和签名是否与开放平台填写的一致，签名请使用该工具：点击下载，常发生在安装了debug版本又安装release版本情况，确定包名签名后卸载微信重装或者清除微信数据再做测试
 * 3）检查发送时的缩略图大小是否超过32k
 * 4）能够调起微信到选择好友列表，但是点击发送后无响应，请检查proguard配置是否对微信SDK代码进行了混淆，建议不要对SDK对混淆，参考以下proguard配置：
 * <p>
 * <p>
 * -keep class com.tencent.mm.opensdk.** {
 * *;
 * }
 * <p>
 * -keep class com.tencent.wxop.** {
 * *;
 * }
 * <p>
 * -keep class com.tencent.mm.sdk.** {
 * *;
 * }
 *
 *
 * 微信分享大小限制
 * public static final int THUMB_LENGTH_LIMIT = 32768; 缩略图大小为32k以下（指的事转为byte[]后的大小）
 * private static final int TITLE_LENGTH_LIMIT = 512;
 * private static final int DESCRIPTION_LENGTH_LIMIT = 1024;
 * private static final int MEDIA_TAG_NAME_LENGTH_LIMIT = 64;
 * private static final int MESSAGE_ACTION_LENGTH_LIMIT = 2048;
 * private static final int MESSAGE_EXT_LENGTH_LIMIT = 2048;
 *
 * <p>
 * 作者：亦枫
 * 链接：https://www.jianshu.com/p/4f64099fcc38
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class ThirdPartApi {


   /* public static final String APP_ID = "wxbe8bacbbc41335fe";
    private static final String APP_SECRET = "8ca1834b8fafe37e3399b90939427356";//这个分享的时候不需要
    private IWXAPI api;
    private static ThirdPartApi thirdPartApi;


    private ThirdPartApi() {
        api = WXAPIFactory.createWXAPI(BaoGuanApplication.getContext(), APP_ID, true);
        api.registerApp(APP_ID);
    }

    public static ThirdPartApi getInstance() {
        if (thirdPartApi == null) {
            thirdPartApi = new ThirdPartApi();
        }
        return thirdPartApi;
    }

    public void handleIntent(Intent intent, IWXAPIEventHandler handler) {
        api.handleIntent(intent, handler);
    }


    public void login() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }


    *//**
     * 获取微信信息
     *
     * @param code
     * @param infoCallback
     *//*
    public void getLoginInfo(String code, final OnGetInfoCallback infoCallback) {

        String urlstr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        HttpUtil.getInstance().getHttp(urlstr, new HttpUtil.OnHttpCallback() {
            @Override
            public void onSuccess(String response) {
                LogUtil.i("test", "wx获得token" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("errcode")) {

                        int errcode = jsonObject.getInt("errcode");
                        infoCallback.onError("error:" + errcode);
                    } else {
                        String access_token = jsonObject.getString("access_token");
                        final String refresh_token = jsonObject.getString("refresh_token");
                        String openid = jsonObject.getString("openid");
//                        String unionid = jsonObject.getString("unionid");//不同应用的id相同

                        String url_getInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
                        HttpUtil.getInstance().getHttp(url_getInfo, new HttpUtil.OnHttpCallback() {
                            @Override
                            public void onSuccess(String response) {

                                try {
                                    JSONObject object = new JSONObject(response);
                                    if (object.has("errcode")) {
                                        int errcode = object.getInt("errcode");
                                        infoCallback.onError("error" + errcode);
                                    } else {
                                        WXInfoEntity infoEntity = new Gson().fromJson(response, WXInfoEntity.class);
//                                        WXInfoEntity wxInfoEntity = JacksonUtil.json2pojo(response, WXInfoEntity.class);
                                        LogUtil.i("test", "获取到" + infoEntity.toString());
                                        infoCallback.onSuccess(infoEntity);

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    infoCallback.onError(e.getMessage());
                                }
                            }

                            @Override
                            public void onError(String error) {
                                infoCallback.onError(error);

                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    onError(e.getMessage());

                }


            }

            @Override
            public void onError(String error) {
                LogUtil.i("test", "wx错误" + error);
                infoCallback.onError(error);

            }
        });

    }

    public interface OnGetInfoCallback {
        void onSuccess(WXInfoEntity infoEntity);

        void onError(String msg);
    }


    public void shareUrl(String url, String title, String description, boolean isTimeline) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);

        msg.title = title;
        msg.description = description;
        Bitmap bitmap = BitmapFactory.decodeResource(BaoGuanApplication.getContext().getResources(), R.mipmap.ic_launcher);

        msg.thumbData = WXUtil.bmpToByteArray(bitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "baoguan";
        req.message = msg;
        req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

        api.sendReq(req);
    }


    public void sharePhoto(Bitmap bitmap, boolean isTimeline) {
//        bitmap = BitmapFactory.decodeResource(BaoGuanApplication.getContext().getResources(),R.mipmap.ic_launcher);

        WXImageObject imgObject = new WXImageObject(bitmap);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObject;

        if (!isTimeline) {
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 100, 200, true);
            msg.thumbData = WXUtil.bmpToByteArray(thumbBmp, true);
        }


        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "baoguan";
        req.message = msg;
        req.scene = isTimeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

        api.sendReq(req);

    }


    *//**
     * 这种方式分享到朋友圈在有些手机上更快
     *
     * @param path
     *//*
    public void sharePhotoTimeLine(String path) {
//        bitmap = BitmapFactory.decodeResource(BaoGuanApplication.getContext().getResources(),R.mipmap.ic_launcher);

        WXImageObject imgObject = new WXImageObject();
        imgObject.imagePath = path;
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObject;

//        if (!isTimeline) {
//            Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 100, 200, true);
//            msg.thumbData = WXUtil.bmpToByteArray(thumbBmp, true);
//        }


        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "baoguan";
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        api.sendReq(req);

    }


     /**
     * 是否安装微信
     *
     * @return
     * /
    public boolean isInstallWeChat() {
        return api.isWXAppInstalled();
    }



*/
}
