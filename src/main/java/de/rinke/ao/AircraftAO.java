package de.rinke.ao;

public class AircraftAO {

    private String id;
    private String id2;
    private String name;
    private String ort;
    private String status;

    private String pilot;

    @Override
    public String toString() {
        return "AircraftAO{" +
                "id='" + id + '\'' +
                ", id2='" + id2 + '\'' +
                ", name='" + name + '\'' +
                ", ort='" + ort + '\'' +
                ", status='" + status + '\'' +
                ", pilot='" + pilot + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
