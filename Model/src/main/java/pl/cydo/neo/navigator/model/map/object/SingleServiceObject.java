package pl.cydo.neo.navigator.model.map.object;

import pl.cydo.neo.navigator.model.map.object.entrance.ObjectEntrance;
import pl.cydo.neo.navigator.model.map.service.ServicePoint;

import java.util.List;

public class SingleServiceObject {
    protected ServicePoint point;
    protected List<ObjectEntrance> entrances;

    public ServicePoint getPoint() {
        return point;
    }

    public void setPoint(ServicePoint point) {
        this.point = point;
    }

    public List<ObjectEntrance> getEntrances() {
        return entrances;
    }

    public void setEntrances(List<ObjectEntrance> entrances) {
        this.entrances = entrances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleServiceObject that = (SingleServiceObject) o;

        if (entrances != null ? !entrances.equals(that.entrances) : that.entrances != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (entrances != null ? entrances.hashCode() : 0);
        return result;
    }
}
