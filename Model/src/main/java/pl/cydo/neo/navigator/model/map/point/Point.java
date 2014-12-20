package pl.cydo.neo.navigator.model.map.point;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@NodeEntity
public abstract class Point {
    @GraphId
    private Long id;

    protected Set<Zone> zones;

    protected BigDecimal latitude;
    protected BigDecimal longitude;

    protected Point() {
    }

    protected Point(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Zone> getZones() {
        return zones;
    }

    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (id != null ? !id.equals(point.id) : point.id != null) return false;
        if (latitude != null ? !latitude.equals(point.latitude) : point.latitude != null) return false;
        if (longitude != null ? !longitude.equals(point.longitude) : point.longitude != null) return false;
        if (zones != null ? !zones.equals(point.zones) : point.zones != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (zones != null ? zones.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", zones=" + zones +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
