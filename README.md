# domain-poi
对 POI 简单封装的业务组件
# 使用实例
```java
//将MultipartFile流转化为熟知的workBook，并配置到上下文中
PoiImportContent.setWorkbook(com.ctspcl.poi.WorkbookFactory.create(file.getInputStream()));
//设置上下文 是否具备表头
PoiImportContent.setImportDataHasHeader(hasHeader);
//设置相关单元格相关约束规则
CustomerCodeRule  customerCodeRule = new CustomerCodeRule();
CqProductCodeRule cqProductCodeRule = new CqProductCodeRule(importConfig);
SzProductCodeRule szProductCodeRule = new SzProductCodeRule(importConfig);
CreditQuotaRule creditQuotaRule = new CreditQuotaRule();
RatioRule ratioRule = new RatioRule();
//设置上下文 中workbook 表头名称以及对应的规则
PoiImportContent.setHeader(new TableHeader(CUSTOMER_CODE,C_PRODUCT_CODE,S_PRODUCT_CODE,
CREDIT_QUOTA,RATIO,VALIDITY_START_TIME,VALIDITY_END_TIME)
                    .setHeaderRule(customerCodeRule,
                    cqProductCodeRule,szProductCodeRule,creditQuotaRule,ratioRule));
//注册行约束规则                   
PoiImportContent.registryRowRule(new ValidateRowRule());
//使用默认解析规则解析 workBook
new DefaultSingleSheetResolvor().resolve();
//使用默认验证器校验数据
new DefaultExcelValidator().startValid();
List<WhitelistQuotaImportBo> list = Lists.newArrayList();
//得到解析后的数据
List<Row> data = PoiImportContent.getTable().getData();
//将解析后的数据转化为业务数据
if (!CollectionUtils.isEmpty(data)){
    data.stream().forEach(row -> {
        WhitelistQuotaImportBo module = new WhitelistQuotaImportBo();
        PoiCastUtil.excelConvertModule(module,row);
        module.setRowNo((long)row.getRowNum() + 1);
        list.add(module);
    });
}
//清除上下文
PoiContent.remove();

```
相关规则如下:
```java
/**客户编码规则*/
class CustomerCodeRule extends StringCellRule { 
        @Override
        public boolean isMatchRule(Cell cell) {
            return super.isMatchRule(cell) &&
                    cell.getStringCellValue().startsWith(CUSTOMER_CODE_START) &&
                    CUSTOMER_CODE_PATTERN.matcher(cell.getStringCellValue()
                    .substring(SPLIT_INDEX)).matches();
        }
}
/**产品编码规则*/
@AllArgsConstructor
public static class CqProductCodeRule extends StringCellRule {

    private BossServiceGeneralQuotaImportConfig importConfig;

    @Override
    public boolean isMatchRule(Cell cell) {
        return importConfig.getCqProductCodeList().contains(cell.getStringCellValue());
    }
}
/**产品编码规则*/
 @AllArgsConstructor
public static class SzProductCodeRule extends StringCellRule {

    private BossServiceGeneralQuotaImportConfig importConfig;

    @Override
    public boolean isMatchRule(Cell cell) {
        return importConfig.getSzProductCodeList().contains(cell.getStringCellValue());
    }
}
/**授信额度规则*/
public static class CreditQuotaRule extends NumericCellRule {
    @Override
    public boolean isMatchRule(Cell cell) {
        if (!super.isMatchRule(cell)){
            return false;
        }
        String creditQuota = cell.getStringCellValue();
        BigDecimal creditQuotaNum = new BigDecimal(creditQuota);
        return creditQuotaNum.compareTo(BigDecimal.ZERO) >= 0;
    }
  }

/**比例规则*/
public static class RatioRule extends NumericCellRule {

        @Override
        public boolean isMatchRule(Cell cell) {
            if (!super.isMatchRule(cell)){
                return false;
            }
            String ratio = cell.getStringCellValue();
            BigDecimal ratioNum = new BigDecimal(ratio);
            return ratioNum.compareTo(BigDecimal.ZERO) >= 0 
            && ratioNum.compareTo(BigDecimal.ONE) <= 0;
        }
  }


/**单行规则或者联合规则*/
public static class ValidateRowRule extends RowRule {
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final DateCellRule dateCellRule = new DateCellRule(df,LocalDateTime.class);

    @Override
    public boolean isMatchRule(Row row) {
        Cell startCell = row.getCell(5);
        Cell endCell = row.getCell(6);
        String startTime = startCell.getStringCellValue();
        String endTime = endCell.getStringCellValue();
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)){
            if (dateCellRule.isMatchRule(startCell) && dateCellRule.isMatchRule(endCell)){
                return ((LocalDateTime)dateCellRule.format(startTime))
                .compareTo(((LocalDateTime)dateCellRule.format(endTime)))<= 0;
            }
        }
        return false;
    }
}

```
