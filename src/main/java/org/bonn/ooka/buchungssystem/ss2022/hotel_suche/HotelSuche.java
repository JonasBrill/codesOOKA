package org.bonn.ooka.buchungssystem.ss2022.hotel_suche;

import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public interface HotelSuche {
	Hotel[] getHotelByName(String name);

	void openSession();

	void closeSession();
}
