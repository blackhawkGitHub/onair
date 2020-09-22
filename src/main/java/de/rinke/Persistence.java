package de.rinke;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Persistence {

	private static String pfad = "daten\\aircraft.xml";
	private static String pfad_airport = "daten\\airport.xml";

	public static void saveAircraft(DatenmanagerAircraft dm) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DatenmanagerAircraft.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File(pfad);
		File fileNew = new File(pfad+"_ba_" + System.currentTimeMillis());
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

	public static void saveAirport(DatenmanagerAirport dm) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DatenmanagerAirport.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		File file = new File(pfad_airport);
		File fileNew = new File(pfad_airport+"_ba_" + System.currentTimeMillis());
		file.renameTo(fileNew);
		jaxbMarshaller.marshal(dm, file);
	}

	public static DatenmanagerAirport loadAirport() throws JAXBException{
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(DatenmanagerAirport.class);
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();
			if(!new File(pfad_airport).exists()){
				return new DatenmanagerAirport();
			}
			DatenmanagerAirport ao = (DatenmanagerAirport) jaxbMarshaller.unmarshal(new File(pfad_airport));

			return ao;
		}
	}
}