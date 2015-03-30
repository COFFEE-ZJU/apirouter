package cn.edu.zju.ccnt.rules;

import java.util.Map;

public interface TransformRule {
	public boolean isRestful();
	public boolean returnsJson();
	public Map<String, String> transParam(Map<String, String> oriParam) throws TransformException;
	public Map<String, String> transResult(Map<String, String> oriResult) throws TransformException;
}
