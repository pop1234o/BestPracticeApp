package com.liyafeng.event.eventbus.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liyafeng on 17/10/2017.
 * 这几个是注解的注解
 *
 * 其实就是忘里面写方法
 * 然后使用就是
 * @CustomSubscribe(xxx) 这个默认是value方法的值
 *
 * @CustomSubscribe(方法名 = 值 , 方法名 = 值)
 *
 */

@Documented //生成文档
@Retention(RetentionPolicy.RUNTIME) //表示保留此注解到什么时候
@Target(ElementType.METHOD)
@Inherited //是否继承
public @interface CustomSubscribe {

    CustomEventBus.ThreadMode threadMode() default CustomEventBus.ThreadMode.POSTING;

    boolean sticky() default false;

    int priority() default 0;

}
