package de.rinke;

import de.rinke.ao.AircraftAO;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public static DatenmanagerAircraft datenmanagerAircraft = new DatenmanagerAircraft();

    public void init() {

        try {

            datenmanagerAircraft = Persistence.loadAircraft();

            umschluesseln();

            boolean createAircraft = false;
            boolean editAircraft = false;
            boolean deleteAircraft = false;

            System.out.println("loading...");
            System.out.println("loading aircrafts...");
            datenmanagerAircraft = Persistence.loadAircraft();

            Thread.sleep(1500);

            this.ausgabe();

            BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
            String str = "";

            System.out.println("Enter 'stop' to quit.");
            do {
                if (deleteAircraft) {
                    System.out.println("Bitte id eingeben");
                    str = getEingabe(obj, null);
                    AircraftAO ao = datenmanagerAircraft.getAO(str);
                    System.out.println(ao);
                    datenmanagerAircraft.getAircraftListe().remove(ao);
                    Persistence.saveAircraft(datenmanagerAircraft);
                    deleteAircraft = false;
                    ausgabe();
                }
                if (editAircraft) {
                    System.out.println("Bitte id eingeben");
                    str = getEingabe(obj, null);
                    AircraftAO ao = datenmanagerAircraft.getAO(str);
                    System.out.println(ao);
                    eingabeAircraft(obj, ao);
                    Persistence.saveAircraft(datenmanagerAircraft);
                    editAircraft = false;
                    this.ausgabe();
                }
                if (createAircraft) {
                    AircraftAO ao = new AircraftAO();
                    ao.setId(""+datenmanagerAircraft.getMaxIdAircraft());
                    System.out.println("create Aircraft");
                    eingabeAircraft(obj, ao);
                    datenmanagerAircraft.getAircraftListe().add(ao);
                    Persistence.saveAircraft(datenmanagerAircraft);
                    createAircraft = false;
                    this.ausgabe();
                } else {
                    str = getEingabe(obj, null);
                    System.err.println(str);
                    if (str.equalsIgnoreCase("ca")) {
                        createAircraft = true;
                    }
                    if (str.equalsIgnoreCase("ea")) {
                        editAircraft = true;
                    }
                    if (str.equalsIgnoreCase("da")) {
                        deleteAircraft = true;
                    }
                }
            } while (!str.equals("stop"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getEingabe(BufferedReader obj, String wert) throws IOException {
        String str = obj.readLine();
        if(str.equalsIgnoreCase("")){
            str= wert;
        }
        if(str.equalsIgnoreCase("---")){
            str= "";
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
        str = getEingabe(obj,ao.getId2());
        ao.setId2(str);
        System.out.println("Name");
         str = getEingabe(obj,ao.getName());
        ao.setName(str);

        System.out.println("Ort");
        str = getEingabe(obj,ao.getOrt());
        ao.setOrt(str);

        System.out.println("Status");
        str = getEingabe(obj,ao.getStatus());
        ao.setStatus(str);

        System.out.println("Pilot");
         str = getEingabe(obj,ao.getPilot());
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
