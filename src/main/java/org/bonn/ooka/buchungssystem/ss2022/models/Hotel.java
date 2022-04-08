package org.bonn.ooka.buchungssystem.ss2022.models;

/**
 * @author Adrian Bähr & Jonas Brill
 */

public class Hotel {
	private final int id;
	private final String name;
	private final String ort;
	private final String sterne;
	private final String kontakt;

	public Hotel(int id, String name, String ort, String sterne, String kontakt) {
		this.id = id;
		this.name = name;
		this.ort = ort;
		this.sterne = sterne;
		this.kontakt = kontakt;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOrt() {
		return ort;
	}

	public String getSterne() {
		return sterne;
	}

	public String getKontakt() {
		return kontakt;
	}
}
