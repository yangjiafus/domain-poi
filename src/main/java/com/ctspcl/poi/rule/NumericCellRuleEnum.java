package com.ctspcl.poi.rule;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/7/24
 **/
public enum NumericCellRuleEnum {
    /**
     *  任意数字（整数或者小数）验证规则
     * */
    NUMERIC_REGEX("^\\d+((\\.\\d+)|\\d*)$");

    private String regex;

    NumericCellRuleEnum(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

}