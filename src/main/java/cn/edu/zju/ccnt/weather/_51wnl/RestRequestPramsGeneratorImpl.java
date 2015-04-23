package cn.edu.zju.ccnt.weather._51wnl;

import java.util.Map;

import org.apache.log4j.Logger;
import cn.edu.zju.ccnt.RestRequestPramsGenerator;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RestRequestPramsGeneratorImpl extends RestRequestPramsGenerator {
	private static final Logger LOGGER = Logger.getLogger(RestRequestPramsGeneratorImpl.class);
	private static MongoClient mongoClient = null;
	private static DBCollection dbCollection = null;
	private static final String MONGO_ADDRESS = "127.0.0.1";
	private static final String MONGO_DBNAME = "muledb";
	private static final String MONGO_COLL_NAME = "cityName2Code";
	private static final String MONGO_USER = "mule";
	private static final String MONGO_PASSWD = "123456";
	
	private static BasicDBObject query = null;
	
	private static boolean connect(){
		if(mongoClient != null) return true;
		DB db;
		
		try {
			mongoClient = new MongoClient(MONGO_ADDRESS);
			db = mongoClient.getDB(MONGO_DBNAME);
			
			if(!db.authenticate(MONGO_USER, MONGO_PASSWD.toCharArray())){
				LOGGER.error("authentication to MongoDB failed!");
				dbCollection = null;
				mongoClient.close();
				mongoClient = null;
				return false;
			}
			
			dbCollection = db.getCollection(MONGO_COLL_NAME);
			return true;
			
		} catch (Exception e) {
			LOGGER.error(e);
			if(mongoClient != null){
				mongoClient.close();
				mongoClient = null;
				dbCollection = null;
			}
			return false;
		}
	}
	
	private String getCityCode(String cityName){
		connect();
		if(query == null)
			query = new BasicDBObject();
		query.put("cityName", cityName);
		DBObject result = dbCollection.findOne(query);
		
		return result == null? null: (String)result.get("cityCode");
	}

	@Override
	public String generateParamString(Map<String, String> params)
			throws Exception {
		String city = params.get("city");
		if(city == null) throw new Exception("missing param city");
		
		String code = getCityCode(city);
		if(code == null) throw new Exception("failed to find city code from mongoDB for: "+city);
		
		return "?cityCode="+code+"&weatherType=0";
	}
}
