package pl.cydo.neo.navigator.web.model;


import java.math.BigDecimal;
import java.util.List;

public class NewPoint {
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private List<Long> categoryIds;

    public NewPoint() {
    }

    public NewPoint(String name, BigDecimal latitude, BigDecimal longitude, List<Long> categoryIds) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryIds = categoryIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }


    @Override
    public String toString() {
        return "NewPoint{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", categoryIds=" + categoryIds +
                '}';
    }
}
