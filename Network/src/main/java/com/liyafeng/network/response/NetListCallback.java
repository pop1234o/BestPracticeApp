package com.liyafeng.network.response;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetListCallback<T> implements Callback<ResponseListEntity<T>> {


    @Override
    public void onResponse(Call<ResponseListEntity<T>> call, Response<ResponseListEntity<T>> response) {
//        LogUtil.i("test", "callback ========" + response.message());
        if (response.code() == 200) {
            ResponseListEntity<T> body = response.body();

            switch (body.getState()) {
                case 10001://成功
                    onSuccess(body.getData());
                    break;
                case 10000://服务端错误
                    onServerFail(body.getMsg());

                    break;
                case 10002://参数错误
                    onServerFail(body.getMsg());
                    break;
                default:
                    onServerFail("unknown error" + body.getState());
            }

        } else {
            onServerFail(response.message());
        }
    }

    @Override
    public void onFailure(Call<ResponseListEntity<T>> call, Throwable t) {

//        if (BaoGuanApplication.isDebug) {
//            if (t.getCause() != null) {
//                LogUtil.e("test", "callback error========" + t.getCause().toString());
//                onNetFail(t.getMessage());
//
//            } else {
//                onNetFail(t.toString());
//            }
//        } else {
//            onNetFail("网络异常");
//        }
    }

    public abstract void onSuccess(List<T> list);

    public abstract void onServerFail(String message);//服务器问题

    public abstract void onNetFail(String message);//网络错误

}