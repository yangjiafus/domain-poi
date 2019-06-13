package com.ctspcl.poi;

import com.ctspcl.poi.imports.PoiImportContent;
import com.ctspcl.poi.imports.TableHeader;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

/**
 * @author JiaFu.yang
 * @description Excel 内存模型
 * @date 2019/2/19
 **/
@Data
public class Table {

    private TableHeader tableHeader;

    private List<Row> data;

    public Table(List<Row> data) {
        this.data = data;
        tableHeader = PoiImportContent.getHeader();
    }
}
