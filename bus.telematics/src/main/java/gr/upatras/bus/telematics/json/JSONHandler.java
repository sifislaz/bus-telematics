package gr.upatras.bus.telematics.json;

import org.json.*;
import java.io.FileWriter;
import java.io.IOException;

public class JSONHandler {
	
	public static JSONObject editJSONObj(JSONObject jso, String id, String value) {
		jso.remove(id);
		jso.put(id, value);
		return jso;
	}
	
	public static JSONArray editJSONArr(JSONArray jsa, int ind, String id, String value) {
		JSONObject obj = jsa.getJSONObject(ind);
		obj.remove(id);
		obj.put(id,value);
		jsa.put(ind,obj);
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
	
}
