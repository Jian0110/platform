package cn.com.ssj.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.DataValidationEvaluator;
import org.apache.poi.ss.usermodel.*;

import cn.com.ssj.dto.ResultObject;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    private static final String [] trueExpressionList = new String[]{"是","TRUE","有"};

    public static String isNull(Cell cell) {
        String string = "";
        if (cell == null) {
            string = "";
            return string;
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            string = cell.getStringCellValue();
            return string;
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            string = String.valueOf(cell.getNumericCellValue());
            return string;
        } else {
            return string;
        }
    }

    public static int getCellValueAsInt(Cell cell) {
        if (cell == null || cell.getCellTypeEnum() != CellType.NUMERIC) return 0;
        return (int) cell.getNumericCellValue();
    }

    public static double getCellValueAsNumeric(Cell cell) {
        if (cell == null) return 0;
        if (cell.getCellTypeEnum() != CellType.NUMERIC) {
            switch (cell.getCellTypeEnum()){
                case STRING:
                    if(StringUtils.isNotBlank(cell.getStringCellValue())){
                        return Double.parseDouble(cell.getStringCellValue());
                    }else{
                        return 0;
                    }
                default:
                    return 0;
            }
        }else{
            return cell.getNumericCellValue();
        }
    }
    public static boolean getCellValueAsBoolean(Cell cell) {
        if (cell == null) return false;
        if (cell.getCellTypeEnum() == CellType.STRING) {
            String value = cell.getStringCellValue().toUpperCase();
            return Arrays.stream(trueExpressionList).anyMatch(s -> s.equals(value));
        }else if(cell.getCellTypeEnum() == CellType.BOOLEAN){
            return cell.getBooleanCellValue();
        }else if(cell.getCellTypeEnum() == CellType.NUMERIC){
            return cell.getNumericCellValue() > 0 ;
        }else{
            return false;
        }
    }

    public static String getCellValueAsString(Cell cell) {
        if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) return "";
        if (cell.getCellTypeEnum() != CellType.STRING) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    DecimalFormat decimalFormat = new DecimalFormat("0.##");
                    return decimalFormat.format(cell.getNumericCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                case BOOLEAN:
                    return Boolean.toString(cell.getBooleanCellValue());
                default:
                    return "";
            }
        }
        return cell.getStringCellValue().trim().replaceAll("\n","");
    }

    public static Date getCellValueAsDate(Cell cell){
        if (cell == null ) return null;
        if(cell.getCellTypeEnum() == CellType.NUMERIC){
            return cell.getDateCellValue();
        }else if(cell.getCellTypeEnum() == CellType.STRING && StringUtils.isNotBlank(cell.getStringCellValue())){
        	//正则先判断是否符合日期格式
        	if (!DateUtils.checkDateStr(cell.getStringCellValue())) {
				return null;
			}
            Date date = DateUtils.toDate(cell.getStringCellValue(),"yyyy/M/d");
            if(date == null){
                date = DateUtils.toDate(cell.getStringCellValue(),"yyyy/MM/dd");
            }
            if(date == null){
                date = DateUtils.toDate(cell.getStringCellValue(),"yyyy-MM-dd");
            }
            if(date == null){
                date = DateUtils.toDate(cell.getStringCellValue(),"yyyy-M-d");
            }
            return date;
        }else{
            return null;
        }

    }
    //excel公式转为公式计算结果
   /* public static Date getCellFormulaToValue(Cell cell,Workbook workbook){
        //.xlsx用XSSFFormulaEvaluator .xls用HSSFFormulaEvaluator

        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        if(cell.getCellTypeEnum() == CellType.FORMULA){
            String furmulaStr = String.valueOf(formulaEvaluator.evaluate(cell).getCellTypeEnum());
            return DateUtils.toDate(furmulaStr,"yyyy-MM-dd");
        }

        return getCellValueAsDate(cell);
    }*/


    /**
     * excel文件比对
     *
     * @param resource 模板文件
     * @param target   比对文件
     * @return 是否一致
     * @throws InvalidFormatException InvalidFormatException
     * @throws IOException            IOException
     */
    public static ResultObject workbookCompare(File resource, File target) throws InvalidFormatException, IOException {
        try (Workbook resourceWorkbook = WorkbookFactory.create(resource); Workbook targetWorkbook = WorkbookFactory.create(target)) {
            int numberOfResourceWorkbookSheets = resourceWorkbook.getNumberOfSheets();
            int numberOfTargetWorkbookSheets = targetWorkbook.getNumberOfSheets();
            if (numberOfResourceWorkbookSheets != numberOfTargetWorkbookSheets) {
                return ResultObject.NACK("sheet 数量不相等");
            }
            if (numberOfResourceWorkbookSheets > 0) {
                for (int i = 0; i < numberOfResourceWorkbookSheets; i++) {
                    Sheet resourceSheet = resourceWorkbook.getSheetAt(0);
                    Sheet targetSheet = targetWorkbook.getSheetAt(0);
                    String resourceSheetName = resourceSheet.getSheetName();
                    String targetSheetName = targetSheet.getSheetName();
                    if (!resourceSheetName.equals(targetSheetName)) {
                        return ResultObject.NACK("第 "+(i+1)+" sheet名称不相等");
                    }
                    Row resourceRow = resourceSheet.getRow(0);
                    Row targetRow = targetSheet.getRow(0);
                    int resourceRowLastCellNum = resourceRow.getLastCellNum();
                    int targetRowLastCellNum = targetRow.getLastCellNum();
                    if (resourceRowLastCellNum != targetRowLastCellNum) {
                        return ResultObject.NACK("第 "+(i+1)+" sheet，第一行，列数不相等 "+resourceRowLastCellNum +":"+ targetRowLastCellNum);
                    }
                    for (int j = 0; j < resourceRowLastCellNum; j++) {
                        Cell resourceCell = resourceRow.getCell(j);
                        Cell targetCell = targetRow.getCell(j);
                        if ((resourceCell == null && targetCell != null)
                                || (resourceCell != null && targetCell == null)) {
                            return ResultObject.NACK("第 "+(i+1)+" sheet，第一行， 第"+(j+1)+"列内容不相等");
                        }
                        if (resourceCell != null && !resourceCell.getStringCellValue().equals(targetCell.getStringCellValue())) {
                            return ResultObject.NACK("第 "+(i+1)+" sheet，第一行， 第"+(j+1)+"列内容不相等");
                        }
                    }
                }
            }
        }
        return ResultObject.ACK();
    }



    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        StringBuilder columnStr = new StringBuilder();
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr.insert(0, ((char) (columnIndex % 26 + (int) 'A')));
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr.toString();
    }
}
