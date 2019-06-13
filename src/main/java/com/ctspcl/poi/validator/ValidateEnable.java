package com.ctspcl.poi.validator;

import com.ctspcl.poi.rule.Rule;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/11
 **/
public interface ValidateEnable<T> {
    /**
     *  验证数据
     * @param t
     * */
    void valid(T t, Rule rule);

}
