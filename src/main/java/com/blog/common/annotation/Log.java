package com.blog.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@interface Log {
    /**
     * 1.日志内容
     */
    String value();
}