import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche.HotelRetrieval;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche.HotelSucheProxy;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HotelSucheProxyTest {

	HotelSucheProxy proxy;

	@BeforeEach
	void setUp() {
		DBAccess dbAccess = new DBAccess();
		HotelRetrieval hotelRetrieval = new HotelRetrieval(dbAccess);
		this.proxy = new HotelSucheProxy(hotelRetrieval);
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
}
