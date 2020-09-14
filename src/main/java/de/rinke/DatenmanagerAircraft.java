package de.rinke;

import de.rinke.ao.AircraftAO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "datenmanager")
public class DatenmanagerAircraft implements Serializable {

    static final long serialVersionUID = 1L;

	private List<AircraftAO> aircraftListe = new ArrayList<AircraftAO>();

    @XmlElement(name = "aircraft")
    public List<AircraftAO> getAircraftListe() {
        return aircraftListe;
    }

    public void setAircraftListe(List<AircraftAO> aircraftListe) {
        this.aircraftListe = aircraftListe;
    }

    public AircraftAO getAO(String id) {
        for (AircraftAO current:aircraftListe) {
            if(current.getId().equalsIgnoreCase(id)){
                return current;
            }
        }
        return null;
    }

    public int getMaxIdAircraft() {
        int id = 0;
        for (AircraftAO current:aircraftListe) {
            if(Integer.parseInt(current.getId())>id){
                id = Integer.parseInt(current.getId());
            }
        }
        id++;
        return id;
    }
}
