package de.rinke.ao;

import de.rinke.rbc.table.annotation.SpaltennameTabelle;

public class PersonalAO {
    //@SpaltennameTabelle(name = "id", pos = 0)
    private String id;
    @SpaltennameTabelle(name = "Name", pos = 0)
    private String name;
    @SpaltennameTabelle(name = "ort", pos = 1)
	private String ort;

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

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

    
}
