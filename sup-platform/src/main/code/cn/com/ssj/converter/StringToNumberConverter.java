package cn.com.ssj.converter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
@Component 
public class StringToNumberConverter implements Converter<String, Number> {
	private static final Logger logger = LogManager.getLogger(StringToNumberConverter.class);
	@Override
	public Number convert(String source) {
		logger.info("StringToNumberConverter convert");
		if (source.length() == 0) {
            return null;
        }
        return NumberUtils.parseNumber(source, Number.class);
	}
	

}