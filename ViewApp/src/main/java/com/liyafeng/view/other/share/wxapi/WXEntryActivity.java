package com.liyafeng.view.other.share.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class WXEntryActivity{}


//
//<activity
//            android:name=".wxapi.WXEntryActivity"
//                    android:label="@string/app_name"
//                    android:theme="@android:style/Theme.Translucent.NoTitleBar"
//                    android:exported="true"
//                    android:taskAffinity="net.sourceforge.simcpux"
//
//                    android:launchMode="singleTask">


//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

//
//public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
//    private static String TAG = "MicroMsg.WXEntryActivity";
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        try {
//            ThirdPartApi.getInstance().handleIntent(getIntent(), this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        setIntent(intent);
//        ThirdPartApi.getInstance().handleIntent(intent, this);
//    }
//
//    @Override
//    public void onReq(BaseReq req) {
//    }
//
//    @Override
//    public void onResp(BaseResp resp) {
//        String result = "";
//
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                result = "成功";
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                result = "取消";
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                result = "未授权";
//                break;
//            case BaseResp.ErrCode.ERR_UNSUPPORT:
//                result = "不支持";
//                break;
//            default:
//                result = "未知";
//                break;
//        }
//
//        LogUtil.i("test", "收到结果" + result);
//        finish();
//
//    }
//
//
//}