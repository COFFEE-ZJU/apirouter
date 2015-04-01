package cn.edu.zju.ccnt.weather.weatherws;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="getWeatherbyCityNameResponse")
public class GetWeatherbyCityNameResponse {
	private String[] getWeatherbyCityNameResult;

	public String[] getGetWeatherbyCityNameResult() {
		return getWeatherbyCityNameResult;
	}

	public void setGetWeatherbyCityNameResult(String[] getWeatherbyCityNameResult) {
		this.getWeatherbyCityNameResult = getWeatherbyCityNameResult;
	}
}
