package de.rinke.ui;

import de.rinke.Controller;
import de.rinke.Persistence;
import de.rinke.ao.AircraftAO;
import de.rinke.ao.AirportAO;

import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class AircraftEditorController {

    private AircraftEditorUI ui = new AircraftEditorUI();
    private AircraftAO ao;

    public void init(AircraftAO ao) {
        this.ao = ao;

        if (ao != null) {
            ui.getTf_id().setText(ao.getId());
            ui.getTf_id2().setText(ao.getId2());
            ui.getTf_namemodell().setText(ao.getName());
            ui.getTf_ort().setText(ao.getOrt());
            ui.getTf_status().setText(ao.getStatus());
            ui.getTf_pilot().setText(ao.getPilot());
        }else{
            ui.getTf_id().setText(UUID.randomUUID().toString());
        }

        this.initListener();
        ui.setModal(true);
        ui.setVisible(true);
    }

    private void initListener() {

        ui.getBtnOK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ao == null) {
                    ao = new AircraftAO();
                    Controller.datenmanagerAircraft.getAircraftListe().add(ao);
                }
                ao.setId(ui.getTf_id().getText());
                ao.setId2(ui.getTf_id2().getText().toUpperCase());
                ao.setName(ui.getTf_namemodell().getText());
                ao.setOrt(ui.getTf_ort().getText().toUpperCase());
                ao.setStatus(ui.getTf_status().getText());
                ao.setPilot(ui.getTf_pilot().getText());
                try {
                    Persistence.saveAircraft(Controller.datenmanagerAircraft);
                    checkAirport(ao.getOrt());
                    ui.setVisible(false);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ui.getBtnAbbrechen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.setVisible(false);
            }
        });
    }

    private void checkAirport(String ort) {
        AirportAO ao = Controller.datenmanagerAirport.getAO(ort);
        if(ao!=null){
            return;
        }
        // airport nicht gefunden, anlegen!
        AirportAO airportAO =  new AirportAO();
        airportAO.setId(UUID.randomUUID().toString());
        airportAO.setName(ort);
        airportAO.setLat(0.0);
        airportAO.setLng(0.0);
        Controller.datenmanagerAirport.getAirportListe().add(airportAO);
    }
}
