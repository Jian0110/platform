package cn.com.ssj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {
	public static final SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
	
	public static Date format(Date date,String formatter) {
		Date result = null;
		SimpleDateFormat simpleDateFormat = null; 
		if(date != null && StringUtils.isNotBlank(formatter)) {
			simpleDateFormat = new SimpleDateFormat(formatter);
			String dateStr = simpleDateFormat.format(date);
			try {
				result = simpleDateFormat.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String toString(Date date,String formatter) {
		String dateStr = null;
		SimpleDateFormat simpleDateFormat = null; 
		if(date != null && StringUtils.isNotBlank(formatter)) {
			simpleDateFormat = new SimpleDateFormat(formatter);
			dateStr = simpleDateFormat.format(date);
		}
		return dateStr;
	}

	public static String getNowDateTimeSerial(){
		Date now = new Date();
		return fullFormat.format(now);
	}

    public static Date toDate(String date, String formatter) {
		if(date == null || StringUtils.isBlank(date) || StringUtils.isBlank(formatter)){
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
    /**
     * 取每月日期为一号
     * @param excelDate
     * @return
     */
    public static Date getDateForMonthFirstDay(Date excelDate) {
		 Date effectiveDate = null ;
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(excelDate);
		 int day = calendar.get(calendar.DAY_OF_MONTH);
		 if (day == 1) {
			return excelDate;
		 }else {
			 String dayDateStr = DateUtils.toString(excelDate, "yyyy-MM-01");
			 effectiveDate = DateUtils.toDate(dayDateStr, "yyyy-MM-dd");
		 	 return effectiveDate;
		 }
	}
    /**
     * 正则检查日期格式
     * @param dateStr
     * @return
     */
    public  static boolean checkDateStr(String dateStr) {
    	String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(dateStr);
		if (!mat.matches()) {
			return false;
		}
		return true;
    }
}
