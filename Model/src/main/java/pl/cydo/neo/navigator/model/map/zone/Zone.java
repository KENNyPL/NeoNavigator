package pl.cydo.neo.navigator.model.map.zone;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import pl.cydo.neo.navigator.model.map.point.Point;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
public class Zone {
    @GraphId
    protected Long id;
    @Indexed
    protected Long latitude;
    @Indexed
    protected Long longitude;
    @Fetch
    @RelatedTo(type = "ZONE_POINT", direction = Direction.BOTH, elementClass = Point.class)
    private Set<Point> points = new HashSet<Point>();

    public Zone() {
    }

    public Zone(Long latitude, Long longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude
                + ", points=" + points +
                '}';
    }

    public void addPoint(Point point) {
        if(points== null){
            points = new LinkedHashSet<Point>();
        }

        points.add(point);
    }
}
