package de.rinke.ui;

import de.rinke.Controller;
import de.rinke.Persistence;
import de.rinke.ao.AirportAO;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.UUID;

public class AirportEditorController {

	private AirportEditorUI ui = new AirportEditorUI();
	private AirportAO ao;

	public void init(AirportAO ao) {
		this.ao = ao;

		if (ao != null) {
			ui.getTf_id().setText(ao.getId());
			ui.getTf_name().setText(ao.getName());
			ui.getTf_lat().setText(""+ao.getLat());
			ui.getTf_lng().setText(""+ao.getLng());
		} else {
			ui.getTf_id().setText(UUID.randomUUID().toString());
		}

		this.initListener();
		ui.setModal(true);
		ui.setVisible(true);
	}

	private void initListener() {

	    ui.getTf_name().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ui.getTf_name().setBackground(Color.WHITE);
                String eingabe = ui.getTf_name().getText();
                if(eingabe.length()==4){
                    for (AirportAO curr:Controller.datenmanagerAirport.getAirportListe()) {
                        if(curr.getName().equalsIgnoreCase(eingabe)){
                            ui.getTf_name().setBackground(Color.RED);
                        }
                    }
                }
            }
        });

		ui.getBtnOK().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ao == null) {
					ao = new AirportAO();
					Controller.datenmanagerAirport.getAirportListe().add(ao);
				}
				ao.setId(ui.getTf_id().getText());
				ao.setName(ui.getTf_name().getText().toUpperCase());
				ao.setLat(Double.parseDouble(ui.getTf_lat().getText().replace(",",".")));
				ao.setLng(Double.parseDouble(ui.getTf_lng().getText().replace(",",".")));
				try {
					Persistence.saveAirport(Controller.datenmanagerAirport);
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
