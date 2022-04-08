package org.bonn.ooka.buchungssystem.ss2022.hotel_suche;

import org.bonn.ooka.buchungssystem.ss2022.DBAccess;
import org.bonn.ooka.buchungssystem.ss2022.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelRetrieval implements HotelSuche{

	private final DBAccess dbAccess;

	public HotelRetrieval(DBAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	@Override
	public Hotel[] getHotelByName(String name) {

		List<String> hotelListAsStrings = dbAccess.getObjects(0, name);

		List<Hotel> hotels = new ArrayList<>();
		for(int i = 0; i + 2 < hotelListAsStrings.size(); i += 3){
			hotels.add(new Hotel(Integer.parseInt(hotelListAsStrings.get(i)),
					hotelListAsStrings.get(i+1),
					hotelListAsStrings.get(i+2),"",""));
		}
		return hotels.toArray(new Hotel[0]);
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
