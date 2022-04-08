import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.caching.CachingImp;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche.HotelSucheProxy;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Bähr & Jonas Brill
 */

class HotelSucheProxyTest {

	HotelSucheProxy proxy;

	@BeforeEach
	void setUp() {
		DBAccess dbAccess = new DBAccess();
		this.proxy = new HotelSucheProxy(dbAccess, new CachingImp());
	}

	@Test
	@DisplayName ("Search for all hotels | expect all hotels")
	void searchForAllHotelsExpectAllHotels() {
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByName("*");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
	}

	@Test
	@DisplayName ("Search for hotel with name Maritim | expect at least one hotel")
	void searchForHotelWithNameMaritimExpectAtLeastOneHotel() {
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByName("Maritim");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
		Arrays.stream(hotels).forEach(hotel -> assertEquals("Maritim", hotel.getName()));
	}

	@Test
	@DisplayName ("multiple search for hotel with name Maritim (cache) | expect at least one hotel")
	void multipleSearchForHotelWithNameMaritimExpectAtLeastOneHotel() {
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByName("Maritim");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
		Arrays.stream(hotels).forEach(hotel -> assertEquals("Maritim", hotel.getName()));

		Hotel[] hotels2 = proxy.getHotelByName("Maritim");

		assertTrue(hotels2.length > 0);
		Arrays.stream(hotels2).forEach(hotel -> assertEquals("Maritim", hotel.getName()));
	}

	@Test
	@DisplayName ("Search for all hotels without cache | expect no nullpointe-exception")
	void searchForAllHotelsWithoutCacheExpectNoNullpointeException() {
		DBAccess dbAccess = new DBAccess();
		HotelSucheProxy proxy = new HotelSucheProxy(dbAccess, null);

		proxy.openSession();
		assertDoesNotThrow(() -> proxy.getHotelByName("*"));
		assertTrue(proxy.getHotelByName("*").length > 0);
		proxy.closeSession();
	}
}
