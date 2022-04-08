package org.bonn.ooka.buchungssystem.ss2022.hotel_suche_erweitert;

import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public interface HotelSucheErweitert {
	Hotel[] getHotelByNameAndOrt(String name, String ort);

	void openSession();

	void closeSession();
}
