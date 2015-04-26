package cn.edu.zju.ccnt.flight.c2c.baidu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.edu.zju.ccnt.RestRequestPramsGenerator;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {
	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final String PARAM_STRING_FORMAT = "?date=%s&dep=%s&arr=%s&appid=4047";

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		Date date = INPUT_DATE_FORMAT.parse(params.get("date"));
		return String.format(PARAM_STRING_FORMAT, OUTPUT_DATE_FORMAT.format(date), params.get("from"), params.get("to"));
	}

}
