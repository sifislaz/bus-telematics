package gr.upatras.bus.telematics.stop;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.upatras.bus.telematics.bus.Bus;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jlaza
 *
 */
@RestController
public class StopController {
	@Autowired
	private IStopService stopService;
	private static final Logger log = LoggerFactory.getLogger(StopController.class);

	/**
	 * @return List<{@link Stop}>
	 */
	@ApiOperation(value = "Retrieves all stops", notes = "This operation retrieves all Bus Stops", response = Stop.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Stop.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/stop/", produces = { "application/json;charset-utf-8" }, method = RequestMethod.GET)
	public List<Stop> getStops() {
		List<Stop> stops = stopService.getAll(); // Get all stops
		return stops;
	}

	/**
	 * @param id
	 * @return {@link Stop}
	 */
	@ApiOperation(value = "Retrieves a Stop by ID", notes = "This operation retrieves a Stop entity. ", response = Stop.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Stop.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/stop/{id}", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Stop getStopById(@ApiParam(value = "Stop ID", required = true) @PathVariable("id") int id) {
		log.info(String.format("Return stop with id %d", id));
		Stop stop = stopService.getById(id); // get stop having the specific id
		return stop; // return stop
	}

	/**
	 * @param id
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Deletes a Stop by ID", notes = "This operation deletes a Stop entity. ", response = Stop.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Stop.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/stop/{id}", produces = {
			"application/json;charset=utf-8" }, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStop(@ApiParam(value = "Stop ID", required=true) @PathVariable("id") int id) {
		try {
			log.info(String.format("Going to delete stop with id %s", id)); // delete stop, return OK
			return new ResponseEntity<Void>(stopService.deleteStop(id), HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("Couldn't resialize response for content type application/json", e);
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * @param s
	 * @return {@link ResponseEntity}
	 */
	@ApiOperation(value = "Creates a Stop", notes = "This operation creates a Stop entity.", response = Stop.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created", response = Stop.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/stop", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.POST)
	public ResponseEntity<Stop> createBus(@ApiParam(value="Stop ID", required=true) @RequestBody Stop s){
		log.info("Will add a new stop");  // Update log
		Stop stop = stopService.createStop(s);
		return new ResponseEntity<Stop>(stop, HttpStatus.OK);  // return the new stop and OK
	}
	
	/**
	 * @param s
	 * @param id
	 * @return the edited {@link Stop} instance
	 */
	@ApiOperation(value = "Updates partially a Stop", nickname = "patchStop", notes = "This operation updates partially a Stop entity.", response = Stop.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated", response = Stop.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
			@ApiResponse(code = 409, message = "Conflict", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
	})
	@RequestMapping(value = "/stop/{id}", produces = { "application/json;charset=utf-8" }, consumes = {
			"application/json;charset=utf-8" }, method = RequestMethod.PATCH)
	public ResponseEntity<Stop> patchStop(
			@ApiParam(value="The new stop attributes", required=true) @RequestBody Stop s, 
			@ApiParam(value="The ID of the edited stop") @PathVariable("id") int id) {
			Stop stop = stopService.editStop(id, s);
			return new ResponseEntity<Stop>(stop, HttpStatus.OK);
	}
}
