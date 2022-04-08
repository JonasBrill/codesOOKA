package org.bonn.ooka.buchungssystem.ss2022.hotel_suche;

import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.caching.Caching;
import org.bonn.ooka.buchungssystem.ss2022.caching.CachingProxy;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class HotelSucheProxy implements HotelSuche {

	private final HotelRetrieval hotelRetrieval;

	public HotelSucheProxy(DBAccess db, Caching cache) {
		CachingProxy cacheProxy = new CachingProxy(cache);
		this.hotelRetrieval = new HotelRetrieval(db, cacheProxy);
	}

	@Override
	public Hotel[] getHotelByName(String name) {
		return hotelRetrieval.getHotelByName(name);
	}

	@Override
	public void openSession() {
		hotelRetrieval.openSession();
	}

	@Override
	public void closeSession() {
		hotelRetrieval.closeSession();
	}
}
