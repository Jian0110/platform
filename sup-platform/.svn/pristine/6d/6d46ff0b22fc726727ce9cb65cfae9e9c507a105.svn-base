package cn.com.ssj.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public abstract class ExcelView<T> extends AbstractXlsxView {

	
	protected Map<String,CellStyle> cellStyles = new HashMap<String,CellStyle>();
	
	public final String FONT_HEADER_TITLE = "FONT_HEADER_TITLE";
	public final String FONT_BODY_NORMAL = "FONT_BODY_NORMAL";
	public final String FONT_BODY_DATETIME = "FONT_BODY_DATETIME";
	
	
	protected void buildExcelFonts(Workbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontName("Microsoft YaHei UI");
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setFont(font);
		cellStyles.put(FONT_HEADER_TITLE, cellStyle);
		
		
		font = workbook.createFont();
		font.setBold(false);
		font.setFontName("Microsoft YaHei UI");
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setFont(font);
		cellStyles.put(FONT_BODY_NORMAL, cellStyle);
		
		//font
		DataFormat dataformat = workbook.createDataFormat();
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setDataFormat(dataformat.getFormat("yyyy/mm/dd"));
        cellStyle.setFont(font);
        cellStyles.put(FONT_BODY_DATETIME, cellStyle);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = (String) model.get("filename");
        String guessCharset = "gb2312"; //根据request的locale 得出可能的编码，中文操作系统通常是gb2312
        fileName = new String(fileName.getBytes(guessCharset), "ISO8859-1"); 
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		List<T> data = (List<T>) model.get("data");
		
		
		buildExcelFonts(workbook);
		buildExcelContent(workbook,data);
		
	}

	protected void buildHeader(Sheet sheet, int startRow, int startColumn ,String... titles) {
		int columns = startColumn;
		Row row = sheet.createRow(startRow);
		Cell cell;
		CellStyle h1 = cellStyles.get(FONT_HEADER_TITLE);

		for(String title : titles) {
			cell = row.createCell(columns++, CellType.STRING);
			cell.setCellStyle(h1);
			cell.setCellValue(title);
		}

	}
	
	protected abstract void buildExcelContent(Workbook workbook,List<T> data);

}
