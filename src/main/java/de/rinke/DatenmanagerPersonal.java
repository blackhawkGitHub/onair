package de.rinke;


import de.rinke.ao.PersonalAO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "datenmanager")
public class DatenmanagerPersonal implements Serializable {

    static final long serialVersionUID = 1L;

	private List<PersonalAO> personalListe = new ArrayList<PersonalAO>();

    @XmlElement(name = "personal")
    public List<PersonalAO> getPersonalListe() {
        return personalListe;
    }

    public void setPersonalListe(List<PersonalAO> personalListe) {
        this.personalListe = personalListe;
    }

    public PersonalAO getAO(String name) {
        for (PersonalAO current:personalListe) {
            if(current.getName().equalsIgnoreCase(name)){
                return current;
            }
        }
        return null;
    }
}
