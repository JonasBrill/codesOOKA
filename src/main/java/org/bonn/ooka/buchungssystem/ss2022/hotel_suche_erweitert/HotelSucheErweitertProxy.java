package org.bonn.ooka.buchungssystem.ss2022.hotel_suche_erweitert;

import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.caching.Caching;
import org.bonn.ooka.buchungssystem.ss2022.caching.CachingProxy;
import org.bonn.ooka.buchungssystem.ss2022.logging.Logger;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class HotelSucheErweitertProxy implements HotelSucheErweitert {

	private final HotelRetrievalErweitert hotelRetrievalErweitert;
	private final Logger log;

	public HotelSucheErweitertProxy(DBAccess db, Logger log, Caching cache) {
		CachingProxy cacheProxy = new CachingProxy(cache);
		this.log = log;
		this.hotelRetrievalErweitert = new HotelRetrievalErweitert(db, cacheProxy);
	}

	@Override
	public Hotel[] getHotelByNameAndOrt(String name, String ort) {
		this.log.log("Zugriff auf Buchungssystem über Methode getHotelByNameAndOrt" +
				"Suchworte: " + name + " " + ort);
		return hotelRetrievalErweitert.getHotelByNameAndOrt(name, ort);
	}

	@Override
	public void openSession() {
		hotelRetrievalErweitert.openSession();
	}

	@Override
	public void closeSession() {
		hotelRetrievalErweitert.closeSession();
	}
}
