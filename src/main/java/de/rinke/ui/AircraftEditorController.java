package de.rinke.ui;

import de.rinke.Controller;
import de.rinke.Persistence;
import de.rinke.ao.AircraftAO;

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
                ao.setId2(ui.getTf_id2().getText());
                ao.setName(ui.getTf_namemodell().getText());
                ao.setOrt(ui.getTf_ort().getText());
                ao.setStatus(ui.getTf_status().getText());
                ao.setPilot(ui.getTf_pilot().getText());
                try {
                    Persistence.saveAircraft(Controller.datenmanagerAircraft);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
                ui.setVisible(false);
            }
        });
        ui.getBtnAbbrechen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ui.setVisible(false);
            }
        });
    }
}
