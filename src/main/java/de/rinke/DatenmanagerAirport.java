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

    public AirportAO getAO(String id) {
        for (AirportAO current:airportListe) {
            if(current.getId().equalsIgnoreCase(id)){
                return current;
            }
        }
        return null;
    }

    public int getMaxIdAirport() {
        int id = 0;
        for (AirportAO current:airportListe) {
            if(Integer.parseInt(current.getId())>id){
                id = Integer.parseInt(current.getId());
            }
        }
        id++;
        return id;
    }

}
