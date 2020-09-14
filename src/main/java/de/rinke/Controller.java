package de.rinke;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.xml.bind.JAXBException;

import de.rinke.ao.AircraftAO;
import de.rinke.ui.AircraftEditorController;
import de.rinke.ui.OnAirUI;

public class Controller {

	public static DatenmanagerAircraft datenmanagerAircraft = new DatenmanagerAircraft();
	private OnAirUI ui = new OnAirUI();

//	geocalc Coordinate lat=new GPSCoordinate(41,.1212);
//	Coordinate lng = new GPSCoordinate(11, .2323);
//	Point point = new Point(lat, lng);
//
//	lat=new DegreeCoordinate(51.4613418);lng=new DegreeCoordinate(-0.3035466);
//	Point point2 = new Point(lat,
//			lng);System.out.println("Distance is "+EarthCalc.getDistance(point2,point)/1000+" km");

//	double dx = 71.5 * (g1.getLat() - g2.getLat());
//	double dy = 111.3 * (g1.getLon() - g2.getLon());
//        return Math.sqrt(dx * dx + dy * dy);

//	String textInBold = "Java_Prof_Level";
//System.out.print("\033[0;1m" + textInBold);

	public void init() {

		try {

			datenmanagerAircraft = Persistence.loadAircraft();

			umschluesseln();
//
            this.updateTable();
            this.initListener();
            ui.setVisible(true);
//
            return;

//			boolean createAircraft = false;
//			boolean editAircraft = false;
//			boolean deleteAircraft = false;
//
//			System.out.println("loading...");
//			System.out.println("loading aircrafts...");
//			datenmanagerAircraft = Persistence.loadAircraft();
//
//			this.ausgabe();
//
//			BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
//			String str = "";
//
//			System.out.println("Enter 'stop' to quit.");
//			do {
//				if (deleteAircraft) {
//					System.out.println("Bitte id eingeben");
//					str = obj.readLine();
//					int id = Integer.parseInt(str);
//					AircraftAO ao = datenmanagerAircraft.getAO(str);
//					System.out.println(ao);
//					datenmanagerAircraft.getAircraftListe().remove(ao);
//					Persistence.saveAircraft(datenmanagerAircraft);
//					deleteAircraft = false;
//					ausgabe();
//				}
//				if (editAircraft) {
//					System.out.println("Bitte id eingeben");
//					str = obj.readLine();
//					int id = Integer.parseInt(str);
//					AircraftAO ao = datenmanagerAircraft.getAO(str);
//					System.out.println(ao);
//					eingabeAircraft(obj, ao);
//					Persistence.saveAircraft(datenmanagerAircraft);
//					editAircraft = false;
//					this.ausgabe();
//				}
//				if (createAircraft) {
//					AircraftAO ao = new AircraftAO();
//					ao.setId(UUID.randomUUID().toString());
//					System.out.println("create Aircraft");
//					eingabeAircraft(obj, ao);
//					datenmanagerAircraft.getAircraftListe().add(ao);
//					Persistence.saveAircraft(datenmanagerAircraft);
//					createAircraft = false;
//					this.ausgabe();
//				} else {
//					str = obj.readLine();
//					System.err.println(str);
//					if (str.equalsIgnoreCase("ca")) {
//						createAircraft = true;
//					}
//					if (str.equalsIgnoreCase("ea")) {
//						editAircraft = true;
//					}
//					if (str.equalsIgnoreCase("da")) {
//						deleteAircraft = true;
//					}
//				}
//			} while (!str.equals("stop"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void umschluesseln() throws JAXBException {
		boolean update = false;
		int id = 0;
		for (AircraftAO current : datenmanagerAircraft.getAircraftListe()) {
			// if (current.getId().length() < 5) {
			if (current.getId().length() > 5) {
				// current.setId(UUID.randomUUID().toString());
				id++;
				current.setId("" + id);
				update = true;
			}
		}
		if (update) {
			Persistence.saveAircraft(datenmanagerAircraft);
		}
	}

	private void updateTable() {
		this.ui.getTable().setDefaultRenderer(Object.class, new TabRend());
		TabModel model = new TabModel(AircraftAO.class);
		for (AircraftAO ao : datenmanagerAircraft.getAircraftListe()) {
			model.add(ao);
		}
		this.ui.getTable().setModel(model);
	}

	private void initListener() {

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

		this.ui.getBtnAircraft().addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AircraftEditorController aec = new AircraftEditorController();
				aec.init(null);
				updateTable();
			}
		});
	}

	private void eingabeAircraft(BufferedReader obj, AircraftAO ao) throws IOException {
		String str;
		System.out.println("Name");
		str = obj.readLine();
		System.err.println(str);
		ao.setName(str);
		System.out.println("Pilot");
		str = obj.readLine();
		System.err.println(str);
		ao.setPilot(str);
		// return str;
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
