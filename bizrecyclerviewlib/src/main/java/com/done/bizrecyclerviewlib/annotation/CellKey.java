package com.done.bizrecyclerviewlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File:com.done.bizrecyclerviewlib.annotation.CellKey
 * Description:修饰cell的唯一性,cellKey是cell的标识，cellClass是key对应的类，
 * 注意这个东西修饰在子类上即可，就是修饰在要出现在页面中的cell
 *
 * @author maruilong
 * @date 2019-10-29
 */
//修饰类
@Target(ElementType.TYPE)
//运行时也要用，反射来用
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CellKey {
    String cellKey() default "";

    String cellClass() default "";
}
