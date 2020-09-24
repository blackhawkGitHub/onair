package de.rinke;

import de.rinke.ao.AircraftAO;

import java.util.List;

public class Sortmanager {
    private int col = 0;
    private int richtung = 0;

    public void sort(int col, DatenmanagerAircraft datenmanagerAircraft) {

        if (this.col == col) {
            //nix
        } else {
            this.col = col;
            richtung = 0;
        }

        richtung++;

        if(richtung==2){
            richtung=0;
        }

        List<AircraftAO> liste = datenmanagerAircraft.getAircraftListe();
        boolean change;
        do {
            change = false;
            for (int i = 0; i < liste.size() - 1; i++) {
                AircraftAO c = liste.get(i);
                AircraftAO d = liste.get(i + 1);

                String a = c.getId2();
                String b = d.getId2();

                if (col == 1) {
                    a = c.getName();
                    b = d.getName();
                }
                if (col == 2) {
                    a = c.getOrt();
                    b = d.getOrt();
                }
                if (col == 3) {
                    a = c.getStatus();
                    b = d.getStatus();
                }
                if (col == 4) {
                    a = c.getPilot();
                    b = d.getPilot();
                }
                if (col == 5) {
                    a = c.getEntfernung();
                    b = d.getEntfernung();
                }

                if (richtung == 1 && a.toLowerCase().compareTo(b.toLowerCase()) > 0
                        ||
                        (richtung == 0 && a.toLowerCase().compareTo(b.toLowerCase()) < 0)) {
                    liste.set(i, d);
                    liste.set(i + 1, c);
                    change = true;
                }
            }
        } while (change);
    }
}
