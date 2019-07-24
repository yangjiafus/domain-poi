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
PoiImportContent.setHeader(new TableHeader(CUSTOMER_CODE,C_PRODUCT_CODE,S_PRODUCT_CODE,CREDIT_QUOTA,RATIO,VALIDITY_START_TIME,VALIDITY_END_TIME)
                    .setHeaderRule(customerCodeRule,cqProductCodeRule,szProductCodeRule,creditQuotaRule,ratioRule));
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
