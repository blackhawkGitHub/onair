package de.rinke.ui;

import de.rinke.Controller;
import de.rinke.Persistence;
import de.rinke.ao.AircraftAO;
import de.rinke.ao.AirportAO;
import de.rinke.ao.PersonalAO;

import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class PersonalEditorController {

	private PersonalEditorUI ui = new PersonalEditorUI();
	private PersonalAO ao;

	public void init(PersonalAO ao) {
		this.ao = ao;

		if (ao != null) {
			ui.getTf_id().setText(ao.getId());
			ui.getTf_namemodell().setText(ao.getName());
			ui.getTf_ort().setText(ao.getOrt());
		} else {
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
					ao = new PersonalAO();
					Controller.datenmanagerPersonal.getPersonalListe().add(ao);
				}
				ao.setId(ui.getTf_id().getText());
				ao.setName(ui.getTf_namemodell().getText());
				ao.setOrt(ui.getTf_ort().getText().toUpperCase());
				try{
				Persistence.savePersonal(Controller.datenmanagerPersonal);
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

}
