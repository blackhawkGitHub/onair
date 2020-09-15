package de.rinke;

import com.peertopark.java.geocalc.*;
import de.rinke.ao.AircraftAO;
import de.rinke.ao.AirportAO;
import de.rinke.ui.AircraftEditorController;
import de.rinke.ui.AirportEditorController;
import de.rinke.ui.OnAirUI;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.event.*;
import java.util.UUID;

public class Controller {

    public static DatenmanagerAircraft datenmanagerAircraft = new DatenmanagerAircraft();
    public static DatenmanagerAirport datenmanagerAirport = new DatenmanagerAirport();
    private OnAirUI ui = new OnAirUI();

    public void init() {

        try {

            datenmanagerAircraft = Persistence.loadAircraft();
            datenmanagerAirport = Persistence.loadAirport();

            umschluesseln();
//
            this.updateTable();
            this.initListener();
            ui.setSize(1300, 500);
            ui.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
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
        this.ui.getTable().setDefaultRenderer(Object.class, new TabRend());
        TabModel model = new TabModel(AircraftAO.class);
        for (AircraftAO ao : datenmanagerAircraft.getAircraftListe()) {
            model.add(ao);
        }
        this.ui.getTable().setModel(model);
        ///
        this.ui.getTable_airport().setDefaultRenderer(Object.class, new TabRend());
        TabModel model2 = new TabModel(AirportAO.class);
        for (AirportAO ao : datenmanagerAirport.getAirportListe()) {
            model2.add(ao);
        }
        this.ui.getTable_airport().setModel(model2);
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
