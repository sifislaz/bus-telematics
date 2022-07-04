package gr.upatras.bus.telematics.bus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jlaza
 * @author sotirissid
 */

@RestController
@Scope("prototype")
public class BusController {
	@Autowired
	private IBusService busService;
	private static final Logger log = LoggerFactory.getLogger(BusController.class);

	/**
	 * @return List<{@link Bus}>
	 */
	@ApiOperation(value = "Retrieves all buses", notes = "This operation retrieves all Bus entities. ", response = Bus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Bus.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/bus/", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public List<Bus> getBus() {

		List<Bus> buses = busService.getAll(); // gets buses
		return buses; // returns buses
	}

	/**
	 * @param id
	 * @return {@link Bus}
	 */
	@ApiOperation(value = "Retrieves a Bus by ID", notes = "This operation retrieves a Bus entity. ", response = Bus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Bus.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/bus/{id}/", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Bus getBusById(@ApiParam(value = "Identifier of Bus", required = true) @PathVariable("id") int id) {
		log.info(String.format("Will return bus with id %s", id)); // update log
		Bus bus = busService.getById(id); // get bus with specific id

		return bus; // return bus
	}

	/**
	 * @param id
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Deletes a Bus by ID", notes = "This operation deletes a Bus entity. ", response = Bus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Bus.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/bus/{id}", produces = { "application/json;charset=utf-8" }, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(
			@ApiParam(value = "Identifier of the Category", required = true) @PathVariable("id") int id) {
		try {
			log.info(String.format("Will delete object with id %s", id)); // log the action
			return new ResponseEntity<Void>(busService.deleteBus(id), HttpStatus.OK); // delete the bus and return ok
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e); // log the error
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR); // return error
		}
	}

	/**
	 * @param b
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Creates a Bus", notes = "This operation creates a Bus entity.", response = Bus.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Bus.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/bus/", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.POST)
	public ResponseEntity<Bus> createBus(
			@ApiParam(value = "The Bus to be created", required = true) @RequestBody Bus b) {
		log.info("Will add a new bus"); // log the action
		Bus bus = busService.createBus(b); // create the bus
		return new ResponseEntity<Bus>(bus, HttpStatus.OK); // response with the bus and OK
	}

	/**
	 * @param b
	 * @param id
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Updates partially a Bus", nickname = "patchBus", notes = "This operation updates partially a Bus entity.", response = Bus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Updated", response = Bus.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/bus/{id}", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.PATCH)
	public ResponseEntity<Bus> patchBus(@ApiParam(value = "The Bus to be updated", required = true) @RequestBody Bus b,
			@ApiParam(value = "Identifier of the Bus", required = true) @PathVariable("id") int id) {
		Bus bus = busService.editBus(id, b); // edit the bus entity
		return new ResponseEntity<Bus>(bus, HttpStatus.OK); // return the patched bus with ok
	}

	/**
	 * @return List<{@link Bus}>
	 * @throws ParseException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@ApiOperation(value = "Return estimated time", notes = "This operation return estimated time ", response = apiClass.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = apiClass.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/bus/{origin}/{destination}", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.GET)
	public apiClass calcTime(@ApiParam(value = "origin", required = true) @PathVariable("origin") String origin,
			@ApiParam(value = "destination", required = true) @PathVariable("destination") String destination)
			throws IOException, InterruptedException, ParseException {
		// gets buses
		apiClass obj = busService.getTime(origin, destination);
		return obj; // returns buses
	}

}