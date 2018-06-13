package com.liyafeng.view.dialog;

import android.content.Context;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CityPicker {

    private void getData(Context context) {
        ArrayList options1Items = new ArrayList<>(50);
        ArrayList options2Items = new ArrayList<List<String>>();
        ArrayList options3Items = new ArrayList<>();

        InputStream is = null;
        try {
            is = context.getAssets().open("data.json");
            int lenght = is.available();
            byte[] buffer = new byte[lenght];
            is.read(buffer);
            String result = new String(buffer, "utf8");
            JSONObject jsonObject = new JSONObject(result);

            JSONObject object = jsonObject.getJSONObject("86");
            Iterator<String> keys = object.keys();
            //省
            while (keys.hasNext()) {
                String key_p = keys.next();//省的key


                JSONObject json_city = jsonObject.getJSONObject(key_p);//市的集合

                Iterator<String> iterator = json_city.keys();

                ArrayList<String> citys = new ArrayList<>();
                ArrayList<List<String>> qu = new ArrayList<>();
                while (iterator.hasNext()) {

                    String key_city = iterator.next();

                    String city = json_city.getString(key_city);
                    citys.add(city);


                    List<String> qus = new ArrayList<>();
                    if (jsonObject.has(key_city)) {

                        //获取区的集合
                        JSONObject json_qu = jsonObject.getJSONObject(key_city);

                        Iterator<String> qu_iterator = json_qu.keys();
                        while (qu_iterator.hasNext()) {//遍历区
                            String key_qu = qu_iterator.next();
                            qus.add(json_qu.getString(key_qu));

                        }
                    }

                    qu.add(qus);

                }


                options2Items.add(citys);
                options3Items.add(qu);
                options1Items.add(object.getString(key_p));//名字


            }

            //市

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * https://github.com/Bigkoo/Android-PickerView
     * <p>
     * compile 'com.contrarywind:Android-PickerView:4.1.4'
     */
    private void showPick() {

//
//        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                List<String> strings = options3Items.get(options1).get(option2);
//                String qu = "";
//                if (options3 < strings.size()) {
//                    qu = strings.get(options3);
//                }
//
//                String tx = options1Items.get(options1)
//                        + options2Items.get(options1).get(option2)
//                        + qu;
//
//                showToast(tx);
//
//            }
//        }).build();
//
//        pvOptions.setPicker(options1Items, options2Items, options3Items);
//        pvOptions.show();
    }


}
