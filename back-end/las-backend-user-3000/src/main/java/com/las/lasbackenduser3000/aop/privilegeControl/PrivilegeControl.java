package com.las.lasbackenduser3000.aop.privilegeControl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunyinuo
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeControl {

    //角色列表
    long[] roleIdList() default {};

}
