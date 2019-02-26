package com.liyafeng.network;


import com.liyafeng.network.response.CompanyDataEntity;
import com.liyafeng.network.response.ResponseListEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaoGuanService {


    /**
     * ======各种注解的用法==
     * https://www.jianshu.com/p/7687365aa946
     * 其实最终还是生成http请求的格式
     *
     * @Query 这个是get 或者post在url后面拼的参数
     * @Filed 这个是post专用，放在body里面，配合 @FormUrlEncoded 使用？？？
     *
     * */


//    @GET("/")
//    Call<String> getContent();


    //获取公司列表
    @POST("/v1/company/list")
    Call<ResponseListEntity<CompanyDataEntity>> getCompanyData(@Query("pageIndex") int pageIndex, @Query("tag_id") long tag_id);
/*
    //获取公司标签
    @GET("v1/company/tag/list")
    Call<ResponseListEntity<CompanyTagEntity>> getCompanyTag();


    //会员列表
    @POST("v1/member/list")
    Call<ResponseListEntity<MemberDataEntity>> getMemberData(@Query("pageIndex") int pageIndex, @Query("industry_id") long industry_id);

    //会员标签
    @GET("v1/member/industry/list")
    Call<ResponseListEntity<MemberColumnEntity>> getMemberColumn();


    //加载/下拉刷新新闻
    @POST("v1/news/list")
    Call<ResponseEntity<NewsDataPageEntity>> getNewsData(@Query("news_id") long news_id, @Query("column_id") long column_id);


    //上拉加载新闻
    @POST("v1/news/list/prev")
    Call<ResponseEntity<NewsDataPageEntity>> getNewsDataDown(@Query("news_id") long news_id, @Query("column_id") long column_id);


    //获取新闻tab
    @GET("v1/news/column/list")
    Call<ResponseListEntity<NewsColumnData>> getNewsColumnData();


    //获取快讯列表
    @POST("v1/flash/list")
    Call<ResponseListEntity<FlashDataEntity>> getFlashData(@Query("flash_id") long flash_id);

    //获取更多快讯列表
    @POST("v1/flash/list/prev")
    Call<ResponseListEntity<FlashDataEntity>> getFlashDataDown(@Query("flash_id") long flash_id);


    //公司详情
    @GET("/v1/company/{company_id}")
    Call<ResponseEntity<CompanyInfoEntity>> loadCompayInfo(@Path("company_id") long id);


    //会员详情
    @POST("v1/member/info")
    Call<ResponseEntity<MemberInfoEntity>> loadMemberInfo(@Query("user_id") long user_id, @Query("member_id") long member_id);


    //微信登录  登录类型 1 微信 2 QQ  3 微博
    @POST("v1/user/thirdpart_login")
    Call<ResponseEntity<UserEntity>> loginWeichat(@Query("login_type") int login_type, @Query("thirdpart_account") String thirdpart_account, @Query("nick_name") String nick_name, @Query("head_icon") String head_icon);


    //发送短信验证码
    @POST("v1/user/sms/send")
    Call<ResponseEntity> sendCode(@Query("phone") String phone);


    //手机登录
    @POST("v1/user/login")
    Call<ResponseEntity<UserEntity>> loginPhone(@Query("phone") String phone, @Query("sms_code") String sms_code);


    //会员兑换
    @POST("v1/member/exchange")
    Call<ResponseEntity<BecomeMemberEntity>> becomeMember(@Query("user_id") long uid, @Query("exchange_code") String exchange_code);


    //添加收货地址
    @POST("v1/member/address/add")
    Call<ResponseEntity<AddAddrEntity>> addAddress(@Query("user_id") long uid,
                                                   @Query("province_name") String province,
                                                   @Query("city_name") String city,
                                                   @Query("region_name") String region,
                                                   @Query("detail_addr") String detail_addr,
                                                   @Query("phone") String phone);

    //获取个人信息
    @POST("v1/user/info")
    Call<ResponseEntity<UserInfoEntity>> getUserInfo(@Query("user_id") long user_id);

    //个人信息修改
    @POST("v1/user/edit")
    Call<ResponseEntity<BaseStatusEntity>> upLoadUserInfo(@Query("user_id") long user_id,
                                                          @Query("phone") String phone,
                                                          @Query("profile") String profile,
                                                          @Query("demands") String demands,
                                                          @Query("specialty") String specialty,
                                                          @Query("nick_name") String nick_name,
                                                          @Query("company_name") String company_name,
                                                          @Query("title") String title,
                                                          @Query("wechat") String wechat
    );

    //上传图片
    @Multipart
    @POST("v1/file/upload")
    Call<ResponseEntity<UpLoadHeadEntity>> upLoadHead(@Query("user_id") long user_id, @Part MultipartBody.Part body);


    //
//    @GET("v1/member/list")//会员列表
//    Call<ResponseListEntity<MemberDataEntity>> getMemberData(@Query("pageIndex") int pageIndex, @Query("tag_id") long tag_id);
//
//    @GET("v1/member/tag/list")//会员标签
//    Call<ResponseListEntity<MemberColumnEntity>> getMemberColumn();
*/


}
