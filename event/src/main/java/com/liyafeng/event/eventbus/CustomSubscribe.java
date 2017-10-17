package com.liyafeng.event.eventbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liyafeng on 17/10/2017.
 * 这几个是注解的注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME) //表示保留此注解到什么时候
@Target(ElementType.METHOD)
@Inherited //是否继承
public @interface CustomSubscribe {


}
