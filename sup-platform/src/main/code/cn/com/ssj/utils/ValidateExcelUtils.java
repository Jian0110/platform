package cn.com.ssj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ValidateExcelUtils extends StringUtils {

	
	
	//私有构造方法：单列模式
	private ValidateExcelUtils(){

    }
	
	private static HashMap<String, List<String>> uniqueMap = new HashMap<>();//检测excel是否字段是否唯一
	private static List<String> codeList = null;
	private static List<String> noidList = null;
	
	/**
	 * 单例模式：返回集合
	 * @return
	 */
	public static HashMap<String, List<String>> initCollection(){
			if (uniqueMap == null && noidList == null && codeList == null) {
				uniqueMap = new HashMap<>();
				codeList = new ArrayList<String>();
				noidList = new ArrayList<String>();
			}
			return uniqueMap;
	}
	
	public static void clearCollection() {
		if (uniqueMap != null && noidList != null && codeList !=null) {
			uniqueMap = null;
			noidList = null;
			codeList = null;
		}
	}
	public static boolean checkUnique(HashMap<String, List<String>> map, String key, String value) {
		//数据加载：只检测身份证号码与编码
		map.put("code", codeList);
		map.put("noid", noidList);
		
		if (map.get(key).size() == 0) {
			map.get(key).add(value);
			return true;
		}
		//信息是否已经存在
		if(map.get(key).contains(value)) {
			return false;
		}
		map.get(key).add(value);
		return true;
	}
	
	
	/**
	 * 
	 * @param idCard正确的身份证
	 * @return 正确的身份证包含的信息:birthday+place of birth+gender+age
	 */
	public static HashMap<String, String> getInfoByidCard(String idCard) {
		HashMap<String, String> map = new HashMap<>();
		if (validateCard(idCard)) {
			map.put("birthPlace", getPlaceOfBirthByIdCard(idCard));
			map.put("birthday", getBirthByIdCard(idCard));
			map.put("gender", getGenderByIdCard(idCard));
			map.put("age", String.valueOf(getAgeByIdCard(idCard)));
		}
		return map;
	}

	/**
	 * 1、验证身份证是否合法
	 */
	public static boolean validateCard(String idCard) {
		String card = idCard.trim();
		if (validateIdCard18(card) || validateIdCard15(card)) {
			return true;
		}
		return false;
	}

	/** 中国公民身份证号码最小长度。 */
	public static final int CHINA_ID_MIN_LENGTH = 15;

	/** 中国公民身份证号码最大长度。 */
	public static final int CHINA_ID_MAX_LENGTH = 18;

	/** 省、直辖市代码表 */
	public static final String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34",
			"35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64",
			"65", "71", "81", "82", "91" };

	/** 每位加权因子 */
	public static final int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/** 第18位校检码 */
	public static final String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
	/** 最低年限 */
	public static final int MIN = 1930;
	public static HashMap<String, String> cityCodes = new HashMap<>();
	/** 台湾身份首字母对应数字 */
	public static HashMap<String, Integer> twFirstCode = new HashMap<>();
	/** 香港身份首字母对应数字 */
	public static HashMap<String, Integer> hkFirstCode = new HashMap<>();

	static {
		cityCodes.put("11", "北京");
		cityCodes.put("12", "天津");
		cityCodes.put("13", "河北");
		cityCodes.put("14", "山西");
		cityCodes.put("15", "内蒙古");
		cityCodes.put("21", "辽宁");
		cityCodes.put("22", "吉林");
		cityCodes.put("23", "黑龙江");
		cityCodes.put("31", "上海");
		cityCodes.put("32", "江苏");
		cityCodes.put("33", "浙江");
		cityCodes.put("34", "安徽");
		cityCodes.put("35", "福建");
		cityCodes.put("36", "江西");
		cityCodes.put("37", "山东");
		cityCodes.put("41", "河南");
		cityCodes.put("42", "湖北");
		cityCodes.put("43", "湖南");
		cityCodes.put("44", "广东");
		cityCodes.put("45", "广西");
		cityCodes.put("46", "海南");
		cityCodes.put("50", "重庆");
		cityCodes.put("51", "四川");
		cityCodes.put("52", "贵州");
		cityCodes.put("53", "云南");
		cityCodes.put("54", "西藏");
		cityCodes.put("61", "陕西");
		cityCodes.put("62", "甘肃");
		cityCodes.put("63", "青海");
		cityCodes.put("64", "宁夏");
		cityCodes.put("65", "新疆");
		cityCodes.put("71", "台湾");
		cityCodes.put("81", "香港");
		cityCodes.put("82", "澳门");
		cityCodes.put("91", "国外");
		twFirstCode.put("A", 10);
		twFirstCode.put("B", 11);
		twFirstCode.put("C", 12);
		twFirstCode.put("D", 13);
		twFirstCode.put("E", 14);
		twFirstCode.put("F", 15);
		twFirstCode.put("G", 16);
		twFirstCode.put("H", 17);
		twFirstCode.put("J", 18);
		twFirstCode.put("K", 19);
		twFirstCode.put("L", 20);
		twFirstCode.put("M", 21);
		twFirstCode.put("N", 22);
		twFirstCode.put("P", 23);
		twFirstCode.put("Q", 24);
		twFirstCode.put("R", 25);
		twFirstCode.put("S", 26);
		twFirstCode.put("T", 27);
		twFirstCode.put("U", 28);
		twFirstCode.put("V", 29);
		twFirstCode.put("X", 30);
		twFirstCode.put("Y", 31);
		twFirstCode.put("W", 32);
		twFirstCode.put("Z", 33);
		twFirstCode.put("I", 34);
		twFirstCode.put("O", 35);
		hkFirstCode.put("A", 1);
		hkFirstCode.put("B", 2);
		hkFirstCode.put("C", 3);
		hkFirstCode.put("R", 18);
		hkFirstCode.put("U", 21);
		hkFirstCode.put("Z", 26);
		hkFirstCode.put("X", 24);
		hkFirstCode.put("W", 23);
		hkFirstCode.put("O", 15);
		hkFirstCode.put("N", 14);
	}

	/**
	 * 将15位身份证号码转换为18位
	 * 
	 * @param idCard
	 *            15位身份编码
	 * @return 18位身份编码
	 */
	public static String conver15CardTo18(String idCard) {
		String idCard18 = "";
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return null;
		}
		if (isNum(idCard)) {
			// 获取出生年月日
			String birthday = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			// 获取出生年(完全表现形式,如：2010)
			String sYear = String.valueOf(cal.get(Calendar.YEAR));
			idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
			// 转换字符数组
			char[] cArr = idCard18.toCharArray();
			if (cArr != null) {
				int[] iCard = converCharToInt(cArr);
				int iSum17 = getPowerSum(iCard);
				// 获取校验位
				String sVal = getCheckCode18(iSum17);
				if (sVal.length() > 0) {
					idCard18 += sVal;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		return idCard18;
	}

	/**
	 * 验证18位身份编码是否合法
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard18(String idCard) {
		boolean bTrue = false;
		if (idCard.length() == CHINA_ID_MAX_LENGTH) {
			// 前17位
			String code17 = idCard.substring(0, 17);
			// 第18位
			String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
			if (isNum(code17)) {
				char[] cArr = code17.toCharArray();
				if (cArr != null) {
					int[] iCard = converCharToInt(cArr);
					int iSum17 = getPowerSum(iCard);
					// 获取校验位
					String val = getCheckCode18(iSum17);
					if (val.length() > 0) {
						if (val.equalsIgnoreCase(code18)) {
							bTrue = true;
						}
					}
				}
			}
		}
		return bTrue;
	}

	/**
	 * 验证15位身份编码是否合法
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 是否合法
	 */
	public static boolean validateIdCard15(String idCard) {
		if (idCard.length() != CHINA_ID_MIN_LENGTH) {
			return false;
		}
		if (isNum(idCard)) {
			String proCode = idCard.substring(0, 2);
			if (cityCodes.get(proCode) == null) {
				return false;
			}
			String birthCode = idCard.substring(6, 12);
			Date birthDate = null;
			try {
				birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			if (birthDate != null)
				cal.setTime(birthDate);
			if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)),
					Integer.valueOf(birthCode.substring(4, 6)))) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 验证台湾身份证号码
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 验证码是否符合
	 */
	public static boolean validateTWCard(String idCard) {
		String start = idCard.substring(0, 1);
		String mid = idCard.substring(1, 9);
		String end = idCard.substring(9, 10);
		Integer iStart = twFirstCode.get(start);
		Integer sum = iStart / 10 + (iStart % 10) * 9;
		char[] chars = mid.toCharArray();
		Integer iflag = 8;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
	}

	/**
	 * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
	 * <p>
	 * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
	 * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
	 * </p>
	 * <p>
	 * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
	 * </p>
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 验证码是否符合
	 */
	public static boolean validateHKCard(String idCard) {
		String card = idCard.replaceAll("[\\(|\\)]", "");
		Integer sum = 0;
		if (card.length() == 9) {
			sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
					+ (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
			card = card.substring(1, 9);
		} else {
			sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
		}
		String mid = card.substring(1, 7);
		String end = card.substring(7, 8);
		char[] chars = mid.toCharArray();
		Integer iflag = 7;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		if (end.toUpperCase().equals("A")) {
			sum = sum + 10;
		} else {
			sum = sum + Integer.valueOf(end);
		}
		return (sum % 11 == 0) ? true : false;
	}

	/**
	 * 将字符数组转换成数字数组
	 * 
	 * @param ca
	 *            字符数组
	 * @return 数字数组
	 */
	public static int[] converCharToInt(char[] ca) {
		int len = ca.length;
		int[] iArr = new int[len];
		try {
			for (int i = 0; i < len; i++) {
				iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return iArr;
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param iArr
	 * @return 身份证编码。
	 */
	public static int getPowerSum(int[] iArr) {
		int iSum = 0;
		if (power.length == iArr.length) {
			for (int i = 0; i < iArr.length; i++) {
				for (int j = 0; j < power.length; j++) {
					if (i == j) {
						iSum = iSum + iArr[i] * power[j];
					}
				}
			}
		}
		return iSum;
	}

	/**
	 * 将power和值与11取模获得余数进行校验码判断
	 * 
	 * @param iSum
	 * @return 校验位
	 */
	public static String getCheckCode18(int iSum) {
		String sCode = "";
		switch (iSum % 11) {
		case 10:
			sCode = "2";
			break;
		case 9:
			sCode = "3";
			break;
		case 8:
			sCode = "4";
			break;
		case 7:
			sCode = "5";
			break;
		case 6:
			sCode = "6";
			break;
		case 5:
			sCode = "7";
			break;
		case 4:
			sCode = "8";
			break;
		case 3:
			sCode = "9";
			break;
		case 2:
			sCode = "x";
			break;
		case 1:
			sCode = "0";
			break;
		case 0:
			sCode = "1";
			break;
		}
		return sCode;
	}

	/**
	 * 根据身份编号获取年龄
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 年龄
	 */
	public static int getAgeByIdCard(String idCard) {
		int iAge = 0;
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String year = idCard.substring(6, 10);
		Calendar cal = Calendar.getInstance();
		int iCurrYear = cal.get(Calendar.YEAR);
		iAge = iCurrYear - Integer.valueOf(year);
		return iAge;
	}

	/**
	 * 根据身份编号获取生日
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyy-MM-dd)
	 */
	public static String getBirthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String birthday = idCard.substring(6, 10)+"-"+idCard.substring(10, 12)+"-"+idCard.substring(12, 14);
		return birthday;
	}

	/**
	 * 根据身份编号获取生日年
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(yyyy)
	 */
	public static Short getYearByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(6, 10));
	}

	/**
	 * 根据身份编号获取生日月
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(MM)
	 */
	public static Short getMonthByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(10, 12));
	}

	/**
	 * 根据身份编号获取生日天
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 生日(dd)
	 */
	public static Short getDateByIdCard(String idCard) {
		Integer len = idCard.length();
		if (len < CHINA_ID_MIN_LENGTH) {
			return null;
		} else if (len == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		return Short.valueOf(idCard.substring(12, 14));
	}

	/**
	 * 根据身份编号获取性别
	 * 
	 * @param idCard
	 *            身份编号
	 * @return 性别(M-男，F-女，N-未知)
	 */
	public static String getGenderByIdCard(String idCard) {
		String sGender = "女";
		if (idCard.length() == CHINA_ID_MIN_LENGTH) {
			idCard = conver15CardTo18(idCard);
		}
		String sCardNum = idCard.substring(16, 17);
		if (Integer.parseInt(sCardNum) % 2 != 0) {
			sGender = "男";
		} else {
			sGender = "女";
		}
		return sGender;
	}

	/**
	 * 根据身份编号获取户籍信息
	 * 
	 * @param idCard
	 * @return 省级编码+市/区+县。
	 */
	public static String getPlaceOfBirthByIdCard(String idCard) {
		int len = idCard.length();
		String sProvinNum = "";
		if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
			sProvinNum = idCard.substring(0, 6);
		}
		return sProvinNum;
	}

	/**
	 * 数字验证
	 * 
	 * @param val
	 * @return 提取的数字。
	 */
	public static boolean isNum(String val) {
		return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
	}

	/**
	 * 验证小于当前日期 是否有效
	 * 
	 * @param iYear
	 *            待验证日期(年)
	 * @param iMonth
	 *            待验证日期(月 1-12)
	 * @param iDate
	 *            待验证日期(日)
	 * @return 是否有效
	 */
	public static boolean valiDate(int iYear, int iMonth, int iDate) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int datePerMonth;
		if (iYear < MIN || iYear >= year) {
			return false;
		}
		if (iMonth < 1 || iMonth > 12) {
			return false;
		}
		switch (iMonth) {
		case 4:
		case 6:
		case 9:
		case 11:
			datePerMonth = 30;
			break;
		case 2:
			boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0)) && (iYear > MIN && iYear < year);
			datePerMonth = dm ? 29 : 28;
			break;
		default:
			datePerMonth = 31;
		}
		return (iDate >= 1) && (iDate <= datePerMonth);
	}
	 
	/**
	  * 
	  * 验证银行卡号 
	  *	校验过程： 
	  *  1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。 
	  *  2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。 
	  *  3、将奇数位总和加上偶数位总和，结果应该可以被10整除。       
	  * 
      */   
       public static boolean checkBankCard(String bankCard) {  
                if(bankCard.length() < 15 || bankCard.length() > 19) {
                    return false;
                }
                char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));  
                if(bit == 'N'){  
                    return false;  
                }  
                return bankCard.charAt(bankCard.length() - 1) == bit;  
       }  

       /** 
        * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
        * @param nonCheckCodeBankCard 
        * @return 
        */  
       public static char getBankCardCheckCode(String nonCheckCodeBankCard){  
           if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0  
                   || !nonCheckCodeBankCard.matches("\\d+")) {  
               //如果传的不是数据返回N  
               return 'N';  
           }  
           char[] chs = nonCheckCodeBankCard.trim().toCharArray();  
           int luhmSum = 0;  
           for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {  
               int k = chs[i] - '0';  
               if(j % 2 == 0) {  
                   k *= 2;  
                   k = k / 10 + k % 10;  
               }  
               luhmSum += k;             
           }  
           return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');  
       }
       /**
        * 3、验证电话号码/手机号码
        * @param tel
        * @return
        */
       public static boolean checkPhone(String tel){
    	   //固定号码
    	   if (tel.startsWith("0") || tel.contains("-") || tel.length() !=11) {
    		   Pattern p1 = null, p2 = null;  
    		   Matcher m = null;  
    		   boolean b = false;  
    		   p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
    		   p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
    		   if (tel.length() > 9) {  
    			   m = p1.matcher(tel);  
    			   b = m.matches();  
    		   } else {  
    			   m = p2.matcher(tel);  
    			   b = m.matches();  
    		   }  
    		   return b;
    	   }
    	   //手机号码
    	   String regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
    	   Pattern p = Pattern.compile(regex);
    	   Matcher m = p.matcher(tel);
    	   return m.matches();
       }
       /**
        * 4、 验证邮箱
        *
        * @param email
        * @return
        */
       public static boolean checkEmail(String email){  
           if(email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){  
               return true;  
           } 
           return false;  
           
       }  
}