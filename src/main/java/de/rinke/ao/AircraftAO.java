package de.rinke.ao;

import de.rinke.rbc.table.annotation.SpaltennameTabelle;

public class AircraftAO {
	@SpaltennameTabelle(name = "id", pos = 0)
	private String id;
	@SpaltennameTabelle(name = "id2", pos = 1)
	private String id2;
	@SpaltennameTabelle(name = "name", pos = 2)
	private String name;
	@SpaltennameTabelle(name = "ort", pos = 3)
	private String ort;
	@SpaltennameTabelle(name = "status", pos = 4)
	private String status;
	@SpaltennameTabelle(name = "pilot", pos = 5)
	private String pilot;
	@SpaltennameTabelle(name = "entf", pos = 6)
	private String entfernung;

	@Override
	public String toString() {
		return "AircraftAO{" +
				"id='" + id + '\'' +
				", id2='" + id2 + '\'' +
				", name='" + name + '\'' +
				", ort='" + ort + '\'' +
				", status='" + status + '\'' +
				", pilot='" + pilot + '\'' +
				", entfernung='" + entfernung + '\'' +
				'}';
	}

	public String getEntfernung() {
		return entfernung;
	}

	public void setEntfernung(String entfernung) {
		this.entfernung = entfernung;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPilot() {
		return pilot;
	}

	public void setPilot(String pilot) {
		this.pilot = pilot;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
