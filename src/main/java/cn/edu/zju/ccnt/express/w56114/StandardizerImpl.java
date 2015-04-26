package cn.edu.zju.ccnt.express.w56114;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.edu.zju.ccnt.ApiResult;
import cn.edu.zju.ccnt.ResultStandardizer;
import cn.edu.zju.ccnt.express.ExpressRecord;
import cn.edu.zju.ccnt.express.ExpressResult;

public class StandardizerImpl extends ResultStandardizer<ExpressResult> {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Override
	public ExpressResult standardize(Object input, Map requestParams)
			throws Exception {
		String json=(String)input;;
		System.out.println(json);
		if(input instanceof String){
			json = (String)input;
		} else {
			throw new ClassCastException("failed to cast "+input.getClass().toString());
		}
		System.out.println(json);
		System.out.println("i am here1");
		Map<String, Object> resData = MAPPER.readValue(json, Map.class);
		List<Map<String, Object>> data = (List<Map<String, Object>>)resData.get("data");
		System.out.println("i am here2");
		ExpressResult ret = new ExpressResult();
		
		ret.setCompanyType((String) resData.get("com"));
		ret.setTrackingNO((String) resData.get("nu"));
		ret.setIsCheck((String) resData.get("ischeck"));
		System.out.println("i am here3");
		List<ExpressRecord>records=new ArrayList<ExpressRecord>();
		for(Map<String, Object> map:data){
			ExpressRecord expressRecord=new ExpressRecord();
			expressRecord.setTime((String) map.get("time"));
			expressRecord.setContext((String) map.get("context"));
			records.add(expressRecord);
		}
		System.out.println("i am here4");
		ret.setData(records);
		
		ret.set_id(ret.getTrackingNO());
		return ret;
	}

}
