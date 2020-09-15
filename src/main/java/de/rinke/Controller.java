package de.rinke;

import com.peertopark.java.geocalc.*;
import de.rinke.ao.AircraftAO;
import de.rinke.ao.AirportAO;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public static DatenmanagerAircraft datenmanagerAircraft = new DatenmanagerAircraft();
    public static DatenmanagerAirport datenmanagerAirport = new DatenmanagerAirport();

    public void init() {

        try {

            System.out.println("loading...");
            System.out.println("loading aircrafts...");
            datenmanagerAircraft = Persistence.loadAircraft();

            System.out.println("loading airports...");
            datenmanagerAirport = Persistence.loadAirport();

            umschluesseln();

            Thread.sleep(1500);

            this.ausgabe();

            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            String str = "";

            System.out.println("Enter 'stop' to quit.");
            do {
                str = getEingabe(obj, null);
                if (str.equalsIgnoreCase("ca")) {
                    AircraftAO ao = new AircraftAO();
                    ao.setId("" + datenmanagerAircraft.getMaxIdAircraft());
                    System.out.println("create Aircraft");
                    eingabeAircraft(obj, ao);
                    datenmanagerAircraft.getAircraftListe().add(ao);
                    Persistence.saveAircraft(datenmanagerAircraft);
                    this.ausgabe();
                }else if (str.equalsIgnoreCase("ea")) {
                    System.out.println("Bitte id eingeben");
                    str = getEingabe(obj, null);
                    AircraftAO ao = datenmanagerAircraft.getAO(str);
                    System.out.println(ao);
                    eingabeAircraft(obj, ao);
                    Persistence.saveAircraft(datenmanagerAircraft);
                    this.ausgabe();
                }else if (str.equalsIgnoreCase("da")) {
                    System.out.println("Bitte id eingeben");
                    str = getEingabe(obj, null);
                    AircraftAO ao = datenmanagerAircraft.getAO(str);
                    System.out.println(ao);
                    datenmanagerAircraft.getAircraftListe().remove(ao);
                    Persistence.saveAircraft(datenmanagerAircraft);
                    ausgabe();
                }else if (str.equalsIgnoreCase("cp")) {
                    AirportAO ao = new AirportAO();
                    ao.setId("" + datenmanagerAirport.getMaxIdAirport());
                    System.out.println("create Airport");
                    eingabeAirport(obj, ao);
                    datenmanagerAirport.getAirportListe().add(ao);
                    Persistence.saveAirport(datenmanagerAirport);
                    this.ausgabeAirport();
                }else if (str.equalsIgnoreCase("ep")) {
                    this.ausgabeAirport();
                    System.out.println("Bitte id eingeben");
                    str = getEingabe(obj, null);
                    AirportAO ao = datenmanagerAirport.getAO(str);
                    System.out.println(ao);
                    eingabeAirport(obj, ao);
                    Persistence.saveAirport(datenmanagerAirport);
                    this.ausgabeAirport();
                }else if (str.equalsIgnoreCase("sa")){
                    this.ausgabe();
                }else if (str.equalsIgnoreCase("sp")){
                    this.ausgabeAirport();
                }
            } while (!str.equals("stop"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eingabeAirport(BufferedReader obj, AirportAO ao) throws IOException {
        String str;
        System.out.println("Name");
        str = getEingabe(obj, ao.getName());
        ao.setName(str);

        System.out.println("lat");
        str = getEingabe(obj, ""+ao.getLat());
        ao.setLat(Double.parseDouble(str));

        System.out.println("lan");
        str = getEingabe(obj, ""+ao.getLan());
        ao.setLan(Double.parseDouble(str));
    }

    private void ausgabeAirport() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Airport");
        System.out.println("--------");
        for (AirportAO ao : datenmanagerAirport.getAirportListe()) {
            // System.out.println(ao.getId()+" "+ao.getName()+" "+ao.getPilot());
            System.out.println(ao);
        }
    }

    private void berechnen() {
        double lat2 = 71.5 * (48.354260 - 53.633673);
        double lon2 = 111.3 * (11.769865 - 09.998024);
        System.out.println("Controller.berechnen1: " + Math.sqrt(lat2 * lat2 + lon2 * lon2));
        //
        Coordinate lat = new GPSCoordinate(48, .354260);
        Coordinate lng = new GPSCoordinate(11, .769865);
        Point point = new Point(lat, lng);
        lat = new DegreeCoordinate(53.633673);
        lng = new DegreeCoordinate(9.998024);
        Point point2 = new Point(lat, lng);
        System.out.println("Distance is " + EarthCalc.getDistance(point2, point) / 1000 + " km");
    }

    private String getEingabe(BufferedReader obj, String wert) throws IOException {
        String str = obj.readLine();
        if (str.equalsIgnoreCase("")) {
            str = wert;
        }
        if (str.equalsIgnoreCase("---")) {
            str = "";
        }
        System.err.println(str);
        return str;
    }

    private void umschluesseln() throws JAXBException {
        boolean update = false;
        int id = 0;
        for (AircraftAO current : datenmanagerAircraft.getAircraftListe()) {
            //if (current.getId().length() < 5) {
            if (current.getId().length() > 5) {
                //current.setId(UUID.randomUUID().toString());
                id++;
                current.setId("" + id);
                update = true;
            }
        }
        if (update) {
            Persistence.saveAircraft(datenmanagerAircraft);
        }
    }

    private void eingabeAircraft(BufferedReader obj, AircraftAO ao) throws IOException {
        String str;
        System.out.println("ID");
        str = getEingabe(obj, ao.getId2());
        ao.setId2(str);
        System.out.println("Name");
        str = getEingabe(obj, ao.getName());
        ao.setName(str);

        System.out.println("Ort");
        str = getEingabe(obj, ao.getOrt());
        ao.setOrt(str.toUpperCase());

        System.out.println("Status");
        str = getEingabe(obj, ao.getStatus());
        ao.setStatus(str);

        System.out.println("Pilot");
        str = getEingabe(obj, ao.getPilot());
        ao.setPilot(str);
        //return str;
    }

    private void ausgabe() throws IOException, InterruptedException {

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Aircraft");
        System.out.println("--------");
        for (AircraftAO ao : datenmanagerAircraft.getAircraftListe()) {
            // System.out.println(ao.getId()+" "+ao.getName()+" "+ao.getPilot());
            System.out.println(ao);
        }
    }
}
