package gr.upatras.bus.telematics.json;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class JSONHandler {
	
	public static JSONObject editJSONObj(JSONObject jso, String id, String value) {
		jso.remove(id);
		jso.put(id, value);
		return jso;
	}
	
	public static JSONArray editJSONArr(JSONArray jsa, int ind, String id, String value) {
		JSONObject obj = (JSONObject)jsa.get(ind);
		obj.remove(id);
		obj.put(id,value);
		jsa.add(ind,obj);
		return jsa;
	}
	
	public static void writeJSONFile(String path, JSONArray jsa) {
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(jsa.toString());
			fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Hashtable<String,Hashtable<String,String>> readJSONFile(String path){
		Hashtable<String,Hashtable<String,String>> jsonList = new Hashtable<String,Hashtable<String,String>>();
		Hashtable<String, String> json = new Hashtable<String, String>();
		try {
			Object arr = new JSONParser().parse(new FileReader(path));
			JSONArray jarr = (JSONArray)arr;
			for(int i=0; i<jarr.size(); i++) {
				JSONObject jo = (JSONObject)jarr.get(i);
				json.put("id",jo.get("id").toString());
				json.put("long", jo.get("long").toString());
				json.put("lat", jo.get("lat").toString());
				json.put("routeId", jo.get("routeId").toString());
				jsonList.put(jo.get("id").toString(),json);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return jsonList;
	}
	
	
	
}
