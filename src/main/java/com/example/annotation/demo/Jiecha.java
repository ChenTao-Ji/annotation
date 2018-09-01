package com.example.annotation.demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Jiecha {

    /**
     * 用户id
     * @return
     */
    int userId();

    /**
     * 另需要验证的类型
     * @return
     */
    String type() default "";

    /**
     * 验证类型的参数
     * @return
     */
    String arge() default "";

}
