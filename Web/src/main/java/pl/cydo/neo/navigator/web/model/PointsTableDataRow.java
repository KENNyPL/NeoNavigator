package pl.cydo.neo.navigator.web.model;

/**
 * Created by root on 14.01.15.
 */
public class PointsTableDataRow {
    private String name;
    private String longitude;
    private String latitude;

    public PointsTableDataRow(String name, String longitude, String latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
