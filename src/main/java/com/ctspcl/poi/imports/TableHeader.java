package com.ctspcl.poi.imports;

import com.ctspcl.poi.rule.CellRule;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class TableHeader {

    private List<HeaderNode> headerNodes;

    public TableHeader(String ... headerValues){
        this.headerNodes = Lists.newArrayList();
        if (headerValues != null && headerValues.length > 0){
            for (int index = 0;index < headerValues.length;index++){
                headerNodes.add(new HeaderNode(index,headerValues[index]));
            }
        }
    }

    public TableHeader setHeaderRule(CellRule ... cellRules){
        if (!CollectionUtils.isEmpty(this.getHeaderNodes())){
            for (int index = 0;index < this.getHeaderNodes().size();index++){
                this.getHeaderNodes().get(index).getCellNode().setRule(cellRules[index]);
            }
        }
        return this;
    }

    public TableHeader setHeaderClass(Class ... clazz){
        if (!CollectionUtils.isEmpty(this.getHeaderNodes())){
            for (int index = 0;index < this.getHeaderNodes().size();index++){
                this.getHeaderNodes().get(index).getCellNode().setClazz(clazz[index]);
            }
        }
        return this;
    }

    public List<HeaderNode> getHeaderNodes(){
        return this.headerNodes;
    }

}
