package gr.upatras.bus.telematics.stop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author jlaza
 *
 *Unit tests for {@link Stop} class
 */
class StopTest {
	Stop test = new Stop(2, "Test", 38.27151,21.75699);
	
	@Test
	void testGetId() {
		assertEquals(2, test.getId(), "Id should've been 2");
	}
	
	@Test
	void testGetName() {
		assertEquals("Test", test.getName(), "Name should've been \"Test\"");
	}

	@Test
	void testSetName() {
		test.setName("TestStop");
		assertEquals("TestStop", test.getName(), "Name should've been \"TestStop\"");
	}


	@Test
	void testGetLongitude() {
		assertEquals(38.27151, test.getLongitude(), "Longitude should've been equal to 38.27151");
	}
	
	@Test
	void testSetLongitude() {
		test.setLongitude(38.25503);
		assertEquals(38.25503, test.getLongitude(), "Longitude should've been equal to 38.25503");
	}
	

	@Test
	void testGetLatitude() {
		assertEquals(21.75699, test.getLatitude(), "Latitude should've been equal to 21.75699");
	}
	
	@Test
	void testSetLatitude() {
		test.setLatitude(21.75699);
		assertEquals(21.75699, test.getLatitude(), "Latitude should've been equal to 21.75699");
	}

}
