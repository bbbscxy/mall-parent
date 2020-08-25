package com.vim.common.utils;

import com.vim.common.annotation.ExcelField;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Excel 工具类
 */
public class ExcelUtils {

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 当前行号
     */
    private int rowNum;

    /**
     * 注解列表（Object[]{ ExcelField, Field }）
     */
    List<Object[]> annotationList = new ArrayList<>();

    /**
     * 导入文件
     * @param file  导入的文件
     * @param cls   数据类型
     */
    public <E> List<E> importExcel(MultipartFile file, Class<E> cls) throws Exception{
        //1.解析类中 ExcelField 注解
        annotationList.clear();
        Field[] fs = cls.getDeclaredFields();
        for (Field field : fs){
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if(null != excelField && (excelField.type().equals(ExcelField.Type.EXPORT_AND_IMPORT) || excelField.type().equals(ExcelField.Type.ONLY_IMPORT))){
                annotationList.add(new Object[]{excelField, field});
            }
        }

        //2.对字段名称进行排序
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return ((ExcelField)o1[0]).sort()-((ExcelField)o2[0]).sort();

            };
        });

        //3.校验文件格式
        String fileName = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        if (StringUtils.isBlank(fileName)){
            throw new RuntimeException("导入文档为空!");
        }else if(!fileName.toLowerCase().endsWith("xls") && !fileName.toLowerCase().endsWith("xlsx")){
            throw new RuntimeException("文档格式不正确!");
        }
        this.wb = new XSSFWorkbook(is);
        if (this.wb.getNumberOfSheets() < 1){
            throw new RuntimeException("文档中没有工作表!");
        }
        this.sheet = this.wb.getSheetAt(0);

        //4、读取数据
        List<E> dataList = new ArrayList<>();
        int firstRowIndex = 1;
        int lastRowIndex = this.sheet.getLastRowNum();
        for(int i = firstRowIndex; i <= lastRowIndex; i++){
            E element = cls.newInstance();
            Row row = this.sheet.getRow(i);
            for(int j = 0; j < annotationList.size(); j++){
                //反射获取值
                Field field = (Field)annotationList.get(j)[1];
                field.setAccessible(true);

                //表格值
                String cellValue = row.getCell(j).getStringCellValue();
                Object val = null;
                if (field.getType() == String.class){
                    val = cellValue;
                }else if (field.getType() == Integer.class){
                    val = Double.valueOf(cellValue).intValue();
                }else if (field.getType() == Long.class){
                    val = Double.valueOf(cellValue).longValue();
                }else if (field.getType() == Double.class){
                    val = Double.valueOf(cellValue);
                }else if (field.getType() == Float.class){
                    val = Float.valueOf(cellValue);
                }else if (field.getType() == Date.class){
                    val = DateUtil.getJavaDate(Double.valueOf(cellValue));
                }

                field.setAccessible(true);
                field.set(element, val);
            }
            dataList.add(element);
        }

        return dataList;
    }

    /**
     * 导出文件
     * @param dataList  数据列表
     * @param cls       数据类型
     * @param out       输出位置
     */
    public void exportExcel(String title, List<?> dataList, Class<?> cls, OutputStream out) throws Exception{
        exportExcel(title, dataList, cls, out, false);
    }

    /**
     * 导出文件
     * @param dataList   数据列表
     * @param cls        数据类型
     * @param out        输出位置
     * @param isTemplate 导出的文件是否是模板
     */
    public void exportExcel(String title, List<?> dataList, Class<?> cls, OutputStream out, boolean isTemplate) throws Exception{
        //1.解析类中 ExcelField 注解
        annotationList.clear();
        Field[] fs = cls.getDeclaredFields();
        for (Field field : fs){
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if(isTemplate){
                if(null != excelField && (excelField.type().equals(ExcelField.Type.EXPORT_AND_IMPORT) || excelField.type().equals(ExcelField.Type.ONLY_IMPORT))){
                    annotationList.add(new Object[]{excelField, field});
                }
            }else{
                if(null != excelField && (excelField.type().equals(ExcelField.Type.EXPORT_AND_IMPORT) || excelField.type().equals(ExcelField.Type.ONLY_EXPORT))){
                    annotationList.add(new Object[]{excelField, field});
                }
            }
        }

        //2.对字段名称进行排序
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return ((ExcelField)o1[0]).sort()-((ExcelField)o2[0]).sort();
            };
        });

        //3.创建表格文件、sheet
        this.wb = new SXSSFWorkbook(500);
        this.sheet = wb.createSheet(title);
        for (int i = 0; i < annotationList.size(); i++) {
            int colWidth = sheet.getColumnWidth(i)*2;
            sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
        }

        //4.创建样式
        Map<String, CellStyle> styles = new HashMap<>();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        style.setFont(titleFont);
        styles.put("title", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        //5.渲染第一行内容：标题栏
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.setHeightInPoints(30);
        for (int i = 0; i < annotationList.size(); i++) {
            Cell cell = headerRow.createCell(i);
            ExcelField excelField = (ExcelField) annotationList.get(i)[0];
            cell.setCellValue(excelField.title());
            cell.setCellStyle(styles.get("title"));
        }

        //6.逐行渲染数据
        for(Object data:dataList){
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.setHeightInPoints(20);
            for(int i = 0; i < annotationList.size(); i++){
                //反射获取值
                Field field = (Field)annotationList.get(i)[1];
                field.setAccessible(true);
                Object val = field.get(data);

                //渲染表格
                Cell dataCell = dataRow.createCell(i);
                dataCell.setCellStyle(styles.get("data"));
                if (val == null){
                    dataCell.setCellValue("");
                } else if (val instanceof String) {
                    dataCell.setCellValue((String) val);
                } else if (val instanceof Integer) {
                    dataCell.setCellValue(String.valueOf(val));
                } else if (val instanceof Long) {
                    dataCell.setCellValue(String.valueOf(val));
                } else if (val instanceof Double) {
                    dataCell.setCellValue(String.valueOf(val));
                } else if (val instanceof Float) {
                    dataCell.setCellValue(String.valueOf(val));
                } else if (val instanceof Date) {
                    DataFormat format = wb.createDataFormat();
                    CellStyle dataStyle = wb.createCellStyle();
                    style.setDataFormat(format.getFormat("yyyy-MM-dd"));

                    dataCell.setCellValue((Date) val);
                    dataCell.setCellStyle(dataStyle);
                }
            }
        }

        //7.生成文件
        wb.write(out);
    }

}