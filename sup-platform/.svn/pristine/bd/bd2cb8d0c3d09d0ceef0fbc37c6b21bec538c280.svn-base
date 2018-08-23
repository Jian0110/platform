package cn.com.ssj.utils;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class FormulaUtils {
	public static List<String> extractVariable(String formula) {
		if(StringUtils.isBlank(formula)) return null;
		List<String> temporary = new ArrayList<String>();
		temporary.add(formula.replaceAll("\\(", "").replaceAll("\\)", ""));
		List<String> result = null;
		result = forSplit(forSplit(forSplit(forSplit(temporary,"\\+"),"\\-"),"\\*"),"\\/");
		return result;
	}
	private static List<String> forSplit(List<String> strings,String regex) {
		List<String> result = new ArrayList<String>();
		for(String str : strings) {
			String [] temporary = str.split(regex);
			result.addAll(Arrays.stream(temporary).collect(Collectors.toList()));
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<String> arrays = new ArrayList<String>();
		arrays.add("(a+b)-c*d/e-(f/d)");
		arrays.add("(a+b)-c*d/e-(f/d)");
		Set<String> keys = new HashSet<String>();
		arrays.stream().map(FormulaUtils::extractVariable).filter(Objects::nonNull).forEach(keys::addAll);
		System.out.println(keys);
	}
}
