package org.bonn.ooka.buchungssystem.ss2022;

import org.bonn.ooka.buchungssystem.ss2022.caching.Caching;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche.HotelSucheProxy;
import org.bonn.ooka.buchungssystem.ss2022.hotel_suche_erweitert.HotelSucheErweitertProxy;
import org.bonn.ooka.buchungssystem.ss2022.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyFassade {
	private final Map<String, Object> proxies;

	public ProxyFassade(Logger log, Caching cache){
		DBAccess dbAccess = new DBAccess();
		proxies= new HashMap<>();
		this.proxies.put("sucheNamen", new HotelSucheProxy(dbAccess, log, cache));
		this.proxies.put("sucheNamenUndOrt", new HotelSucheErweitertProxy(dbAccess, log, cache));
	}

	public List<String> listProxies(){
		return new ArrayList<>(this.proxies.keySet());
	}

	public Object getProxy(String name){
		return this.proxies.get(name);
	}
}
