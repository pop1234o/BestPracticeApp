package com.liyafeng.dependencyinjection;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by liyafeng on 2018/1/12.
 */

public class IntDefine {

   public static final int TYPE_1 = 26;
   public static final int TYPE_2 = 24;
   public static final int TYPE_3 = 22;
    @IntDef({TYPE_1, TYPE_2, TYPE_3})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public void setType(@Type int type){

    }

    public static void main(String[] args) {
        //如果这样写编译会报错2
//        new IntDefine().setType(1);

        //这样也不行，必须写常亮名
//        new IntDefine().setType(22);
    }
}
