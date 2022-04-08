package org.bonn.ooka.buchungssystem.ss2022.hotel_suche;

import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

public class HotelSucheProxy implements HotelSuche {

	private final HotelRetrieval hotelRetrieval;

	public HotelSucheProxy(HotelRetrieval hotelRetrieval) {
		this.hotelRetrieval = hotelRetrieval;
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
