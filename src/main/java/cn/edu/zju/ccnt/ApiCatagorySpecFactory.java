package cn.edu.zju.ccnt;

import java.util.ArrayList;
import java.util.List;

public class ApiCatagorySpecFactory {
	private ApiCatagorySpecFactory(){};
	private static final List<ApiCatagorySpec<? extends ApiResult>> API_CATAGORY_SPECS = new ArrayList<ApiCatagorySpec<? extends ApiResult>>();
	
	static{
		 API_CATAGORY_SPECS.add(new cn.edu.zju.ccnt.weather.ApiCatagorySpecImpl());
		 API_CATAGORY_SPECS.add(new cn.edu.zju.ccnt.train.s2s.ApiCatagorySpecImpl());
		 API_CATAGORY_SPECS.add(new cn.edu.zju.ccnt.train.trainno.ApiCatagorySpecImpl());
	}
	
	public static List<ApiCatagorySpec<? extends ApiResult>> getApiCatagorySpecs(){
		return API_CATAGORY_SPECS;
	}
}
