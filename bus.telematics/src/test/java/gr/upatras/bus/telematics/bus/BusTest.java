package gr.upatras.bus.telematics.bus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author jlaza
 *
 *Unit tests for {@link Bus} class
 */
class BusTest {
	Bus test = new Bus(2,38.27151, 21.75699,6);
	
	@Test
	void testBus() {
		assertThrows(IllegalArgumentException.class, ()-> new Bus(-2,31.5664, 21.3567));
		assertThrows(IllegalArgumentException.class, ()-> new Bus(-2,31.5664, 21.3567,4));
	}
	
	
	@Test
	void testGetRouteId() {
		assertEquals(6, test.getRouteId(), "Route ID should've been 6");
	}
	
	@Test
	void testSetRouteId() {
		test.setRouteId(5);
		assertEquals(5, test.getRouteId(), "Route ID should've been 5");
	}

	@Test
	void testGetLongitude() {
		assertEquals(38.27151, test.getLongitude(), "Bus longitude should've been 38.27151");
	}

	@Test
	void testGetLatitude() {
		assertEquals(21.75699, test.getLatitude(), "Bus latitude should've been 21.75699");
	}
	
	@Test
	void testSetLongitude() {
		test.setLongitude(38.25024);
		assertEquals(38.25024, test.getLongitude(), "Bus longitude should've been 38.25024");
	}
	
	@Test
	void testSetLatitude() {
		test.setLatitude(21.72305);
		assertEquals(21.72305, test.getLatitude(), "Bus latitude should've been 38.25024");
	}

	@Test
	void testGetId() {
		assertEquals(2,test.getId(), "Bus id should've been 2");
	}

	@Test
	void testMoveTo() {
		test.moveTo(38.29035, 21.69464);
		assertEquals(38.29035, test.getLongitude(), "Bus longitude should've been 38.29035");
		assertEquals(21.69464, test.getLatitude(), "Bus latitude should've been 21.69464");
	}

}
