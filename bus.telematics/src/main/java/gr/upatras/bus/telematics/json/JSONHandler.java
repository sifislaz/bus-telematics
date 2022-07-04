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

	/**
	 * @param path
	 * @param o    This method gets the path of the file to be created and the
	 *             object which will be transformed into JSON format and produces
	 *             the file
	 */
	public static void createJSONFile(String path, Object o) {
		ObjectMapper m = new ObjectMapper();
		try {
			m.writeValue(Paths.get(path).toFile(), o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path
	 * @return the object that is produced from parsing the JSON file at the given
	 *         path
	 */
	public static Object readJSONFile(String path) {
		ObjectMapper m = new ObjectMapper();
		try {
			Object o = m.readValue(Paths.get(path).toFile(), Object.class);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
