import org.bonn.ooka.buchungssystem.ss2022.ProxyFassade;
import org.bonn.ooka.buchungssystem.ss2022.caching.CachingImp;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche.HotelSucheProxy;
import org.bonn.ooka.buchungssystem.ss2022.logging.Logger;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class ProxyFassadeTest {

	private ProxyFassade proxyFassade;

	@BeforeEach
	public void setUp() {
		proxyFassade = new ProxyFassade(new Logger(), new CachingImp());
	}

	@Test
	@DisplayName ("get list of proxies")
	void getListOfProxies() {
		List<String> proxies = new ArrayList<>();
		proxies.add("sucheNamenUndOrt");
		proxies.add("sucheNamen");

		proxyFassade.listProxies().forEach(proxy -> assertTrue(proxies.contains(proxy)));
	}

	@Test
	@DisplayName ("get specific proxy")
	void getSpecificProxy() {
		HotelSucheProxy proxy = (HotelSucheProxy) proxyFassade.getProxy("sucheNamen");
		proxy.openSession();
		Hotel[] hotels = proxy.getHotelByName("*");
		proxy.closeSession();

		assertTrue(hotels.length > 0);
	}
}
