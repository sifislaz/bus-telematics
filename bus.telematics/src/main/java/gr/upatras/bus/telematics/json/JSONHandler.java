package gr.upatras.bus.telematics.json;


import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jlaza
 *
 */
public class JSONHandler {
	
	public static void createJSONFile(String path, Object o) {
		ObjectMapper m = new ObjectMapper();
		try {
			m.writeValue(Paths.get(path).toFile(), o);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object readJSONFile(String path) {
		ObjectMapper m = new ObjectMapper();
		try {
			Object o = m.readValue(Paths.get(path).toFile(), Object.class);
			return o;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
