package gr.upatras.bus.telematics.bus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.upatras.bus.telematics.json.JSONHandler;

/**
 * @author jlaza
 * @author sotirissid
 */

@Service
@Scope("prototype")
public class BusService implements IBusService {
	// private IRouteService routeService;

	List<LinkedHashMap> busesJSON;

	public ArrayList<Bus> buses = new ArrayList<Bus>();

	public BusService() {
		super();
		busesJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("bus.json");
		for (LinkedHashMap m : busesJSON) {
			// get bus key values
			int id = Integer.parseInt(m.get("id").toString());
			double lng = Double.parseDouble(m.get("long").toString());
			double lat = Double.parseDouble(m.get("lat").toString());
			int routeId = Integer.parseInt(m.get("routeId").toString());
			if (id < 0)
				throw new IllegalArgumentException("id can't be negative");
			if (routeId < 0)
				throw new IllegalArgumentException("routeId can't be negative");
			if (routeId == 0) {
				buses.add(new Bus(id, lng, lat)); // Create the bus
			} else {
				buses.add(new Bus(id, lng, lat, routeId)); // Create the bus
			}
		}
	}

	/**
	 * Return the list of available buses
	 */
	@Override
	public ArrayList<Bus> getAll() {
		return buses;
	}

	/**
	 * @param id Return the bus with the id if exists
	 */
	@Override
	public Bus getById(int id) {
		if (id < 0)
			throw new IllegalArgumentException("id can't be negative");
		for (Bus b : buses) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}

	/**
	 * @param b
	 * @return the created {@link Bus}
	 */
	@Override
	public Bus createBus(Bus b) {
		buses.add(b);
		JSONHandler.createJSONFile("bus.json", buses);
		return b;
	}

	/**
	 * @param id
	 * @param bus
	 * @return the changed {@link Bus} with changed id
	 */
	@Override
	public Bus editBus(int id, Bus bus) {
		Bus temp = getById(id);
		if (temp != null) {
			temp.setLongitude(bus.getLongitude());
			temp.setLatitude(bus.getLatitude());
			temp.setRouteId(bus.getRouteId());
		}

		return temp;
	}

	/**
	 * Deletes bus and updates the json file
	 */
	@Override
	public Void deleteBus(int id) {
		if (id < 0)
			throw new IllegalArgumentException("id can't be negative");
		for (Bus b : buses) {
			if (b.getId() == id) {
				buses.remove(b);
				JSONHandler.createJSONFile("bus.json", buses);
				break;
			}

		}
		return null;
	}

	/**
	 * @param origin
	 * @param destination
	 * @return an {@link apiClass} object, containing the response of the Google API
	 *         which contains the time needed to transport from origin to
	 *         destination
	 */
	public apiClass getTime(String origin, String destination)
			throws IOException, InterruptedException, ParseException {
		apiClass temp = new apiClass(origin, destination);
		return temp;
	}

	/**
	 * @param id
	 * @param bus Updates the {@link Bus} object which has the given id, with the
	 *            attributes of the given {@link Bus} object It also updates the
	 *            JSON file with the new information
	 */
	public void updateBus(int id, Bus bus) {
		int counter = 0;
		for (Bus b : buses) {
			if (b.getId() == id) {
				buses.remove(b);
				buses.add(counter, bus);
				break;
			}
			counter++;
		}
		JSONHandler.createJSONFile("bus.json", buses);

	}

	/**
	 * @return an {@link ArrayList} of the {@link Bus} IDs
	 */
	public ArrayList<Integer> getBusIds() {
		ArrayList<Integer> bus_ids = new ArrayList<Integer>();
		int id;
		for (Bus b : buses) {
			id = b.getId();
			bus_ids.add(id);
		}

		return bus_ids;

	}

}
