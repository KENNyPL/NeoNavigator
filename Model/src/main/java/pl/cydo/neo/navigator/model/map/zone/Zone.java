package pl.cydo.neo.navigator.model.map.zone;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import pl.cydo.neo.navigator.model.map.point.Point;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Zone {
    @GraphId
    protected Long id;
    @Indexed
    protected Long latitude;
    @Indexed
    protected Long longitude;
    @RelatedTo(type = "NORTH_NEIGHBOR", direction = Direction.OUTGOING)
    private Zone northNeighbor;
    @RelatedTo(type = "SOUTH_NEIGHBOR", direction = Direction.OUTGOING)
    private Zone southNeighbor;
    @RelatedTo(type = "EAST_NEIGHBOR", direction = Direction.OUTGOING)
    private Zone eastNeighbor;
    @RelatedTo(type = "WEST_NEIGHBOR", direction = Direction.OUTGOING)
    private Zone westNeighbor;
    @Fetch
    @RelatedTo(type = "ZONE_POINT", direction = Direction.BOTH, elementClass =Point.class )
    private Set<Point> points = new HashSet<Point>();

    public Zone() {
    }

    public Zone(Long longitude, Long latitude) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
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

    public Zone getNorthNeighbor() {
        return northNeighbor;
    }

    public void setNorthNeighbor(Zone northNeighbor) {
        this.northNeighbor = northNeighbor;
    }

    public Zone getSouthNeighbor() {
        return southNeighbor;
    }

    public void setSouthNeighbor(Zone southNeighbor) {
        this.southNeighbor = southNeighbor;
    }

    public Zone getEastNeighbor() {
        return eastNeighbor;
    }

    public void setEastNeighbor(Zone eastNeighbor) {
        this.eastNeighbor = eastNeighbor;
    }

    public Zone getWestNeighbor() {
        return westNeighbor;
    }

    public void setWestNeighbor(Zone westNeighbor) {
        this.westNeighbor = westNeighbor;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        if (eastNeighbor != null ? !eastNeighbor.equals(zone.eastNeighbor) : zone.eastNeighbor != null) return false;
        if (id != null ? !id.equals(zone.id) : zone.id != null) return false;
        if (latitude != null ? !latitude.equals(zone.latitude) : zone.latitude != null) return false;
        if (longitude != null ? !longitude.equals(zone.longitude) : zone.longitude != null) return false;
        if (northNeighbor != null ? !northNeighbor.equals(zone.northNeighbor) : zone.northNeighbor != null)
            return false;
        if (points != null ? !points.equals(zone.points) : zone.points != null) return false;
        if (southNeighbor != null ? !southNeighbor.equals(zone.southNeighbor) : zone.southNeighbor != null)
            return false;
        if (westNeighbor != null ? !westNeighbor.equals(zone.westNeighbor) : zone.westNeighbor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (northNeighbor != null ? northNeighbor.hashCode() : 0);
        result = 31 * result + (southNeighbor != null ? southNeighbor.hashCode() : 0);
        result = 31 * result + (eastNeighbor != null ? eastNeighbor.hashCode() : 0);
        result = 31 * result + (westNeighbor != null ? westNeighbor.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude
                + (northNeighbor != null ? ", northNeighbor=[" + northNeighbor.getLongitude() + ", " + northNeighbor.getLatitude() + "]" : "null")
                + (southNeighbor != null ? ", southNeighbor=[" + southNeighbor.getLongitude() + ", " + southNeighbor.getLatitude() + "]" : "null")
                + (eastNeighbor != null ? ", eastNeighbor=[" + eastNeighbor.getLongitude() + ", " + eastNeighbor.getLatitude() + "]" : "null")
                + (westNeighbor != null ? ", westNeighbor=[" + westNeighbor.getLongitude() + ", " + westNeighbor.getLatitude() + "]" : "null")
                + ", points=" + points +
                '}';
    }
}
