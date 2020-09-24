package de.rinke;

import com.peertopark.java.geocalc.*;
import de.rinke.ao.AircraftAO;
import de.rinke.ao.AirportAO;
import de.rinke.ao.PersonalAO;
import de.rinke.ui.AircraftEditorController;
import de.rinke.ui.AirportEditorController;
import de.rinke.ui.OnAirUI;
import de.rinke.ui.PersonalEditorController;
import rbc.klassen.FileManagerRBC;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.xml.bind.JAXBException;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class Controller {

	public static DatenmanagerAircraft datenmanagerAircraft = new DatenmanagerAircraft();
	public static DatenmanagerAirport datenmanagerAirport = new DatenmanagerAirport();
	public static DatenmanagerPersonal datenmanagerPersonal = new DatenmanagerPersonal();
	private OnAirUI ui = new OnAirUI();
	private Sortmanager sortmanager = new Sortmanager();

	public void init() {

		try {

			datenmanagerAircraft = Persistence.loadAircraft();
			datenmanagerAirport = Persistence.loadAirport();
			datenmanagerPersonal = Persistence.loadPersonal();

//            umschluesseln();
//            importDaten();

			this.updateTable();
			this.initListener();
			ui.setLocation(0,0);
			ui.setSize(1800, 500);
			ui.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void importDaten() throws IOException, JAXBException {
		File datenFile = new File("airports.csv");
		if (datenFile.exists()) {
			List<String> rohDaten = FileManagerRBC.readFileRAF(datenFile.getAbsoluteFile());
			for (String current : rohDaten) {
				System.out.println("Controller.importDaten: " + current);
				String[] daten = current.split(";");
				String name = daten[2].replaceAll("\"", "").trim();
				AirportAO ao = datenmanagerAirport.getAO(name);
				if (ao != null) {
					ao.setLat(Double.parseDouble(daten[8].replaceAll("\"", "").trim()));
					ao.setLng(Double.parseDouble(daten[9].replaceAll("\"", "").trim()));
				} else {
					ao = new AirportAO();
					ao.setId(UUID.randomUUID().toString());
					ao.setName(name);
					ao.setLat(Double.parseDouble(daten[8].replaceAll("\"", "").trim()));
					ao.setLng(Double.parseDouble(daten[9].replaceAll("\"", "").trim()));
					datenmanagerAirport.getAirportListe().add(ao);
				}
			}
			Persistence.saveAirport(datenmanagerAirport);
		}
	}

	private void umschluesseln() throws JAXBException {
		boolean update = false;
		for (AircraftAO current : datenmanagerAircraft.getAircraftListe()) {
			if (current.getId().length() < 5) {
				current.setId(UUID.randomUUID().toString());
				update = true;
			}
		}
		if (update) {
			Persistence.saveAircraft(datenmanagerAircraft);
		}
		update = false;
		for (AirportAO current : datenmanagerAirport.getAirportListe()) {
			if (current.getId().length() < 5) {
				current.setId(UUID.randomUUID().toString());
				update = true;
			}
		}
		if (update) {
			Persistence.saveAirport(datenmanagerAirport);
		}
	}

	private void updateTable() {

		updatePersonalAircraft();

		this.ui.getTable().setDefaultRenderer(Object.class, new TabRend());
		TabModel model = new TabModel(AircraftAO.class);
		for (AircraftAO ao : datenmanagerAircraft.getAircraftListe()) {
			model.add(ao);
		}
		this.ui.getTable().setModel(model);
		//
		this.ui.getTable_airport().setDefaultRenderer(Object.class, new TabRend());
		TabModel model2 = new TabModel(AirportAO.class);
		for (AirportAO ao : datenmanagerAirport.getAirportListe()) {
			model2.add(ao);
		}
		this.ui.getTable_airport().setModel(model2);
		//
		this.ui.getTable_personal().setDefaultRenderer(Object.class, new TabRend());
		TabModel model3 = new TabModel(PersonalAO.class);
		for (PersonalAO ao : datenmanagerPersonal.getPersonalListe()) {
			model3.add(ao);
		}
		this.ui.getTable_personal().setModel(model3);
	}

	private void updatePersonalAircraft() {
		for (AircraftAO ao :datenmanagerAircraft.getAircraftListe()) {
			ao.setPilot("");
			for (PersonalAO personalAO:datenmanagerPersonal.getPersonalListe()) {
				if(ao.getOrt()!=null){
					if(personalAO.getOrt()!=null){
						if(ao.getOrt().equalsIgnoreCase(personalAO.getOrt())){
							ao.setPilot(ao.getPilot()+personalAO.getName()+";");
						}
					}
				}
			}
		}
	}

	private void initListener() {

		this.ui.getTf_airport().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String eingabe = ((JTextField) e.getSource()).getText();
				if (eingabe != null && eingabe.length() == 4) {
					Controller.berechnen(eingabe);
					updateTable();
				}
			}
		});

		this.ui.getBtnPersonal().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{
					PersonalEditorController aec = new PersonalEditorController();
					aec.init(null);
					updateTable();
				}
			}
		});

		this.ui.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = ((JTableHeader) e.getSource()).getTable();
				int col = table.columnAtPoint(e.getPoint());
				String name = table.getColumnName(col);

			//	if(col==0){
					// id2
					sortmanager.sort(col,datenmanagerAircraft);
					updateTable();
			//	}
			}
		});

		this.ui.getTable_airport().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable table = (JTable) e.getSource();
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					AirportAO ao = (AirportAO) ((TabModel) table.getModel()).getValueAt(row);
					AirportEditorController aec = new AirportEditorController();
					aec.init(ao);
					updateTable();
				}
			}
		});

		this.ui.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable table = (JTable) e.getSource();
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					AircraftAO ao = (AircraftAO) ((TabModel) table.getModel()).getValueAt(row);
					AircraftEditorController aec = new AircraftEditorController();
					aec.init(ao);
					updateTable();
				}
			}
		});

		this.ui.getBtnAirport().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AirportEditorController aec = new AirportEditorController();
				aec.init(null);
				updateTable();
			}
		});

		this.ui.getBtnAircraft().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AircraftEditorController aec = new AircraftEditorController();
				aec.init(null);
				updateTable();
			}
		});
	}

	private static void berechnen(String name) {
		AirportAO dest = datenmanagerAirport.getAO(name);
		for (AircraftAO ao : datenmanagerAircraft.getAircraftListe()) {
			ao.setEntfernung("");
		}
		if (dest != null) {
			for (AircraftAO ao : datenmanagerAircraft.getAircraftListe()) {
				String airport = ao.getOrt();
				AirportAO airportAO = datenmanagerAirport.getAO(airport);
				if (airportAO != null) {
					double entf = Controller.berechnen(dest, airportAO);
					ao.setEntfernung("" + entf);
				}
			}
		}
	}

	private static double berechnen(AirportAO dest, AirportAO ao) {

		Coordinate lat = new DegreeCoordinate(dest.getLat());
		Coordinate lng = new DegreeCoordinate(dest.getLng());
		Point point = new Point(lat, lng);

		lat = new DegreeCoordinate(ao.getLat());
		lng = new DegreeCoordinate(ao.getLng());
		Point point2 = new Point(lat, lng);

		double entf = EarthCalc.getDistance(point2, point) / 1000;

		System.out.println("Distance is " + entf + " km");
		return entf;
	}
}
