package org.bonn.ooka.buchungssystem.ss2022.hotel_suche;

import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

public interface HotelSuche {
	Hotel[] getHotelByName(String name);
	void openSession();
	void closeSession();
}
