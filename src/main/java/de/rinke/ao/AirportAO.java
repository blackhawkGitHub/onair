package de.rinke.ao;

import de.rinke.rbc.table.annotation.SpaltennameTabelle;

public class AirportAO {
    @SpaltennameTabelle(name = "id", pos = 0)
    private String id;
    @SpaltennameTabelle(name = "Name", pos = 1)
    private String name;
    @SpaltennameTabelle(name = "lat", pos = 2)
    private Double lat;
    @SpaltennameTabelle(name = "lng", pos = 3)
    private Double lng;

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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
