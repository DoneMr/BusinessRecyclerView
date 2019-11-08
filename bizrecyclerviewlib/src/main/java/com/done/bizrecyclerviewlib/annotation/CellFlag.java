package com.done.bizrecyclerviewlib.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File:com.done.businessrecyclerview.annotation.CellFlag
 * Description:注解 给予cell更加灵活的配置和复用能力
 * 改注解仅作用于类上，且不可被集成，且保留到运行时需要被读取，根据{@link CellFlag#flag()}的唯一标识选择唯一的cell
 *
 * @author maruilong
 * @date 2019-11-08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CellFlag {
    String flag() default "";
}
