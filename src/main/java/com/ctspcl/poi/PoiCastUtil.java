package com.ctspcl.poi;

import com.ctspcl.poi.imports.PoiImportContent;
import com.ctspcl.poi.rule.BooleanCellRule;
import com.ctspcl.poi.rule.DateCellRule;
import com.ctspcl.poi.rule.NumericCellRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import java.lang.reflect.Field;

/**
 *  反射不能处理所有的类型，穷举不利于 封闭修改 开放拓展 原则<br/>
 *  目前仅支持JAVA常见数据类型,不支持 enum 枚举类型
 * @author JiaFu.yang
 * @description
 * @date 2019/2/21
 **/
@Slf4j
public class PoiCastUtil {

    public static void excelConvertModule (Object object,Row row) {
        PoiImportContent.getHeader().getHeaderNodes().stream().forEach(headerNode ->{
            try {
                Field field = object.getClass().getDeclaredField(headerNode.getHeaderNodeName());
                field.setAccessible(true);
                //可以让浮点数精度不发生变化
                String value = row.getCell(headerNode.getHeaderNodeIndex()).getStringCellValue();
                if (headerNode.getCellNode().getRule() instanceof NumericCellRule){
                    if (Number.class.isAssignableFrom(field.getType())){
                        field.set(object,field.getType().getConstructor(String.class).newInstance(value));
                        return;
                    }
                    if (int.class.isAssignableFrom(field.getType())){
                        field.set(object,Integer.valueOf(value));
                        return;
                    }
                    if (long.class.isAssignableFrom(field.getType())){
                        field.set(object,Long.valueOf(value));
                        return;
                    }
                    if (float.class.isAssignableFrom(field.getType())){
                        field.set(object,Float.valueOf(value));
                        return;
                    }
                    if (double.class.isAssignableFrom(field.getType())){
                        field.set(object,Double.valueOf(value));
                        return;
                    }
                    if (byte.class.isAssignableFrom(field.getType())){
                        field.set(object,Byte.valueOf(value));
                        return;
                    }
                    if (short.class.isAssignableFrom(field.getType())){
                        field.set(object,Short.valueOf(value));
                        return;
                    }
                }
                if (headerNode.getCellNode().getRule() instanceof DateCellRule){
                    field.set(object,((DateFormatEnable) headerNode.getCellNode().getRule()).format(value));
                    return;
                }
                if (headerNode.getCellNode().getRule() instanceof BooleanCellRule){
                    field.set(object,Boolean.valueOf(value));
                    return;
                }
                field.set(object,value);
                return;
            }catch (Exception e){
            }
        });

    }

}
