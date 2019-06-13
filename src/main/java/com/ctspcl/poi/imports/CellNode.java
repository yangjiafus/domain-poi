package com.ctspcl.poi.imports;

import com.ctspcl.poi.rule.CellRule;
import com.ctspcl.poi.rule.Rule;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class CellNode {

    private Integer index;

    private String name;

    private CellRule rule;

    private Class<?> clazz;


    public CellNode() {
        this(null,null,null);
    }

    public CellNode(Integer index, String name, CellRule rule) {
        this(index,name,rule,null);
    }

    public CellNode(Integer index, String name, CellRule rule, Class clazz) {
        this.index = index;
        this.name = name;
        this.rule = rule;
        this.clazz = clazz;
    }


    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public CellRule getRule() {
        return rule;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setRule(CellRule rule) {
        this.rule = rule;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
