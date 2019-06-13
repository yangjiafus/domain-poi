package com.ctspcl.poi.export;


import java.util.List;

/**
 * @author JiaFu.yang
 * @description
 * @date 2019/6/10
 **/
public interface EnableExport {
    /**
     *  获取导出元数据
     * @return
     * */
    <T> List<T> getCellValueList();
}
