package org.bonn.ooka.buchungssystem.ss2022.hotel_suche_erweitert;

import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.caching.Caching;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class HotelRetrievalErweitert implements HotelSucheErweitert {

	private final DBAccess dbAccess;
	private final Caching cache;

	public HotelRetrievalErweitert(DBAccess dbAccess, Caching cache) {
		this.cache = cache;
		this.dbAccess = dbAccess;
	}

	@Override
	public Hotel[] getHotelByNameAndOrt(String name, String ort) {

		List<Object> hotelsTmp = this.cache.fetchResult(name + "_" + ort);
		if (hotelsTmp == null) {
			List<String> hotelListAsStrings = dbAccess.getObjects(0,
					name+ "%\' AND ort like \'%" + ort + "%\' AND \'%\' = \'");

			List<Hotel> hotels = new ArrayList<>();
			for (int i = 0; i + 2 < hotelListAsStrings.size(); i += 3) {
				hotels.add(new Hotel(Integer.parseInt(hotelListAsStrings.get(i)), hotelListAsStrings.get(i + 1), hotelListAsStrings.get(i + 2), "", ""));
			}
			this.cache.cacheResult(name + "_" + ort, hotels.stream().map(Object.class::cast).collect(Collectors.toList()));
			return hotels.toArray(new Hotel[0]);
		}
		return hotelsTmp.toArray(new Hotel[0]);
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
