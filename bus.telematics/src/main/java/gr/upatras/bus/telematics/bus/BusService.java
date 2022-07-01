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
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.upatras.bus.telematics.json.JSONHandler;

/**
 * @author jlaza
 *
 */
@Service
public class BusService implements IBusService {
	// private IRouteService routeService;
	List<LinkedHashMap> busesJSON;
	ArrayList<Bus> buses = new ArrayList<Bus>();

	public BusService() {
		super();
		busesJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("bus.json");
		for (LinkedHashMap m : busesJSON) {
			// get bus key values
			int id = Integer.parseInt(m.get("id").toString());
			double lng = Double.parseDouble(m.get("long").toString());
			double lat = Double.parseDouble(m.get("lat").toString());
			int routeId = Integer.parseInt(m.get("routeId").toString());
			if(id < 0) throw new IllegalArgumentException("id can't be negative");
			if(routeId < 0) throw new IllegalArgumentException("routeId can't be negative");
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
		if(id < 0) throw new IllegalArgumentException("id can't be negative");
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
		return b;
	}
	
	/**
	 * @param id
	 *@param bus
	 *@return the changed {@link Bus} with changed id
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

	@Override
	public Void deleteBus(int id) {
		if(id < 0) throw new IllegalArgumentException("id can't be negative");
		for (Bus b : buses) {
			if (b.getId() == id) {
				buses.remove(b);
				break;
			}
		}
		return null;
	}
	
	public apiClass getTime(String origin,String destination) throws IOException, InterruptedException, ParseException {
		apiClass temp= new apiClass(origin,destination);
		return temp;
	}
	
	// function that gets an origin and a destination and return the estimated time (as a string) between those two points
	//origin and destination can be city names or coordinates 
	

}
