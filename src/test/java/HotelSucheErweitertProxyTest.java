import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.caching.CachingImp;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche_erweitert.HotelSucheErweitertProxy;
import org.bonn.ooka.buchungssystem.ss2022.logging.Logger;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class HotelSucheErweitertProxyTest {

	HotelSucheErweitertProxy proxy;

	@BeforeEach
	void setUp() {
		DBAccess dbAccess = new DBAccess();
		this.proxy = new HotelSucheErweitertProxy(dbAccess, new Logger(), new CachingImp());
	}

	@Test
	@DisplayName ("Search for all hotels in Hamburg| expect all hotels in Hamburg")
	void searchForAllHotelsInHamburgExpectAllHotelsInHamburg() {
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByNameAndOrt("", "Hamburg");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
	}

	@Test
	@DisplayName ("Search for hotel with name Maritim in Bonn | expect at least one hotel")
	void searchForHotelWithNameMaritimExpectAtLeastOneHotel() {
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByNameAndOrt("Maritim", "Bonn");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
		Arrays.stream(hotels).forEach(hotel -> assertEquals("Maritim", hotel.getName()));
		Arrays.stream(hotels).forEach(hotel -> assertEquals("Bonn", hotel.getOrt()));
	}

	@Test
	@DisplayName ("multiple search for hotel with name Maritim in Bonn (cache) | expect at least one hotel")
	void multipleSearchForHotelWithNameMaritimExpectAtLeastOneHotel() {
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByNameAndOrt("Maritim", "Bonn");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
		Arrays.stream(hotels).forEach(hotel -> assertEquals("Maritim", hotel.getName()));

		Hotel[] hotels2 = proxy.getHotelByNameAndOrt("Maritim", "Bonn");

		assertTrue(hotels2.length > 0);
		Arrays.stream(hotels2).forEach(hotel -> assertEquals("Maritim", hotel.getName()));
	}

	@Test
	@DisplayName ("Search for all hotels without cache | expect no nullpointe-exception")
	void searchForAllHotelsWithoutCacheExpectNoNullpointeException() {
		DBAccess dbAccess = new DBAccess();
		HotelSucheErweitertProxy proxy = new HotelSucheErweitertProxy(dbAccess, new Logger(), null);

		proxy.openSession();
		assertDoesNotThrow(() -> proxy.getHotelByNameAndOrt("", "Hamburg"));
		assertTrue(proxy.getHotelByNameAndOrt("", "Hamburg").length > 0);
		proxy.closeSession();
	}
}
