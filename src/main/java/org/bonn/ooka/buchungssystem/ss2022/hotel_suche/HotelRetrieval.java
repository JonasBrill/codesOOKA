package org.bonn.ooka.buchungssystem.ss2022.hotel_suche;

import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.caching.Caching;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelRetrieval implements HotelSuche {

	private final DBAccess dbAccess;
	private final Caching cache;

	public HotelRetrieval(DBAccess dbAccess, Caching cache) {
		this.cache = cache;
		this.dbAccess = dbAccess;
	}

	@Override
	public Hotel[] getHotelByName(String name) {

		try {
			return this.cache.fetchResult(name).toArray(new Hotel[0]);
		} catch (Exception e) {
			System.err.println("Cache miss");
			List<String> hotelListAsStrings = dbAccess.getObjects(0, name);

			List<Hotel> hotels = new ArrayList<>();
			for (int i = 0; i + 2 < hotelListAsStrings.size(); i += 3) {
				hotels.add(new Hotel(Integer.parseInt(hotelListAsStrings.get(i)), hotelListAsStrings.get(i + 1), hotelListAsStrings.get(i + 2), "", ""));
			}
			this.cache.cacheResult(name, hotels.stream().map(Object.class::cast).collect(Collectors.toList()));
			return hotels.toArray(new Hotel[0]);
		}
	}

	@Override
	public void openSession() {
		dbAccess.openConnection();
	}

	@Override
	public void closeSession() {
		dbAccess.closeConnection();
	}
}
