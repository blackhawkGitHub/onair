package de.rinke;

import de.rinke.ao.AircraftAO;
import de.rinke.ao.AirportAO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DatenmanagerAirport {

        private List<AirportAO> airportListe = new ArrayList<AirportAO>();


    public List<AirportAO> getAirportListe() {
        return airportListe;
    }

    public void setAirportListe(List<AirportAO> airportListe) {
        this.airportListe = airportListe;
    }
}
