package de.rinke;

import de.rinke.ao.AirportAO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "datenmanager")
public class DatenmanagerAirport implements Serializable {

    static final long serialVersionUID = 1L;

	private List<AirportAO> airportListe = new ArrayList<AirportAO>();

    @XmlElement(name = "airport")
    public List<AirportAO> getAirportListe() {
        return airportListe;
    }

    public void setAirportListe(List<AirportAO> airportListe) {
        this.airportListe = airportListe;
    }

    public AirportAO getAO(String name) {
        for (AirportAO current:airportListe) {
            if(current.getName().equalsIgnoreCase(name)){
                return current;
            }
        }
        return null;
    }
}
