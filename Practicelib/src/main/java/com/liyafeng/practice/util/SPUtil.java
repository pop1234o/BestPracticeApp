package com.liyafeng.practice.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

public class SPUtil {


    private static String NAME_USER = "user";
    private static String NAME_VERSION = "version";

    private static String KEY_ID = "id";
    private static String KEY_TOKEN = "token";
    private static String KEY_VIP = "vip";
    private static String NICK_NAME = "nick_name";
    private static String COMPANY_NAME = "company_name";
    private static String TITLE = "title";
    private static String HEAD_ICON = "head_icon";
    private static String EXPIRE_DATE = "expire_date";
    private static String PHONE = "phone";


    private static String NAME_SEARCH = "search";
    private static String KEY_SEARCH = "search";


//    public static void putUser(UserEntity userEntity) {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_USER, Context.MODE_PRIVATE);
//        preferences.edit()
//                .putLong(KEY_ID, userEntity.getUid())
//                .putString(KEY_TOKEN, userEntity.getToken())
//                .putInt(KEY_VIP, userEntity.getVip())
//                .putString(NICK_NAME, userEntity.getNick_name())
//                .putString(COMPANY_NAME, userEntity.getCompany_name())
//                .putString(TITLE, userEntity.getTitle())
//                .putString(HEAD_ICON, userEntity.getHead_icon())
//                .putString(EXPIRE_DATE, userEntity.getExpire_date())
//                .putString(PHONE, userEntity.getPhone())
//                .commit();
//    }
//
//    public static void putPhone(String phone) {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_USER, Context.MODE_PRIVATE);
//        preferences.edit()
//                .putString(PHONE, phone)
//                .commit();
//    }
//
//    public static UserEntity getCurrentUser() {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_USER, Context.MODE_PRIVATE);
//        long id = preferences.getLong(KEY_ID, -1);
//        String token = preferences.getString(KEY_TOKEN, "");
//        int vip = preferences.getInt(KEY_VIP, 0);
//
//        String nick_name = preferences.getString(NICK_NAME, "");
//        String company_name = preferences.getString(COMPANY_NAME, "");
//        String title = preferences.getString(TITLE, "");
//        String head_icon = preferences.getString(HEAD_ICON, "");
//        String expire_date = preferences.getString(EXPIRE_DATE, "");
//        String phone = preferences.getString(PHONE, "");
//        if (id > 0) {
//            return new UserEntity(id, token, vip, nick_name, company_name, title, head_icon, expire_date, phone);
//        }
//        return null;
//
//    }
//
//    public static void clearUser() {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_USER, Context.MODE_PRIVATE);
//        preferences.edit().clear().commit();
//    }
//
//    /**
//     * 显示弹窗了，而且选择了下次再说
//     *
//     * @param android_version
//     * @return
//     */
//    public static boolean isShowed(String android_version) {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_USER, Context.MODE_PRIVATE);
//        String string = preferences.getString(android_version, "");
//        return !TextUtils.isEmpty(string);
//    }
//
//    public static void putShowed(String android_version) {
//
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_USER, Context.MODE_PRIVATE);
//        preferences.edit().putString(android_version, "show").commit();
//
//    }
//
//    public static List<String> getSearchHistory() {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_SEARCH, Context.MODE_PRIVATE);
//        String string = preferences.getString(KEY_SEARCH, "");
//        try {
//            if (TextUtils.isEmpty(string)) {
//                return new LinkedList<String>();
//            }
//            JSONArray jsonArray = new JSONArray(string);
//            LinkedList<String> strings = new LinkedList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                strings.add(jsonArray.getString(i));
//            }
//            return strings;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return new LinkedList<String>();
//    }
//
//    public static void putSearchHistory(List<String> list) {
//        SharedPreferences preferences = BaoGuanApplication.getContext().getSharedPreferences(NAME_SEARCH, Context.MODE_PRIVATE);
//        if (!AppUtil.isEmpty(list)) {
//            JSONArray jsonArray = new JSONArray(list);
//            preferences.edit().putString(KEY_SEARCH, jsonArray.toString()).apply();
//        }
//    }
}
