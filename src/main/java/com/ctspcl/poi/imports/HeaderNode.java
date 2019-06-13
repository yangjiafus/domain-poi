package com.ctspcl.poi.imports;


import com.ctspcl.poi.rule.CellRule;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public class HeaderNode {

    private CellNode cellNode;

    public HeaderNode(Integer index, String name) {
        this.cellNode = new CellNode(index,name,null,String.class);
    }

    public HeaderNode(Integer index, String name, CellRule rule) {
        this.cellNode = new CellNode(index,name,rule,String.class);
    }

    public int getHeaderNodeIndex(){
        return this.cellNode.getIndex();
    }

    public String getHeaderNodeName(){
        return this.cellNode.getName();
    }

    public CellNode getCellNode() {
        return cellNode;
    }

}
