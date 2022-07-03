package gr.upatras.bus.telematics.route;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RouteTest {
Route r= new Route(15,"start","destination");
	@Test
	void testId() {
		assertEquals(15, r.getId(), "Id should've been 15");
	}
	
	@Test
	void testStart() {
		assertEquals("start", r.getStart(), "start should've been start");
	}
	
	@Test
	void testDestination() {
		assertEquals("destination", r.getDestination(), "destination should've been destination");
	}

}
