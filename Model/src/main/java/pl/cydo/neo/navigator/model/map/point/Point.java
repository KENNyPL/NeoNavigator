package pl.cydo.neo.navigator.model.map.point;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import pl.cydo.neo.navigator.model.map.zone.Zone;

import java.math.BigDecimal;
import java.util.Set;

@NodeEntity
public abstract class Point {
    @GraphId
    private Long id;

    private Set<Zone> zones;

    protected BigDecimal latitude;
    protected BigDecimal longitude;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (latitude != null ? !latitude.equals(point.latitude) : point.latitude != null) return false;
        if (longitude != null ? !longitude.equals(point.longitude) : point.longitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
