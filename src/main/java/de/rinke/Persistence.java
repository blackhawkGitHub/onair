package de.rinke;

import de.rinke.ao.AircraftAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Persistence {

	private static String pfad = "daten\\aircraft.xml";

	public static void saveAircraft(DatenmanagerAircraft dm) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DatenmanagerAircraft.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File(pfad);
		File fileNew = new File(pfad + System.currentTimeMillis());
		file.renameTo(fileNew);
		jaxbMarshaller.marshal(dm, file);
	}

	public static DatenmanagerAircraft loadAircraft() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DatenmanagerAircraft.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
		if(!new File(pfad).exists()){
			return new DatenmanagerAircraft();
		}
		DatenmanagerAircraft ao = (DatenmanagerAircraft) jaxbMarshaller.unmarshal(new File(pfad));

		return ao;
	}

    public static DatenmanagerAirport loadAirport() {
    }
}