package pl.cydo.neo.navigator.web.controller.rest.data;


public class PointsFromPositionRequestData {
    private Long longitude;
    private Long latitude;
    private Long distanceKm;

    public PointsFromPositionRequestData() {
    }

    public PointsFromPositionRequestData(Long longitude, Long latitude, Long distanceKm) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.distanceKm = distanceKm;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Long distanceKm) {
        this.distanceKm = distanceKm;
    }
}
