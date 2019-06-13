package com.ctspcl.poi.rule;


/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/11
 **/
public interface Rule<T> {

    boolean isMatchRule(T t);

}
