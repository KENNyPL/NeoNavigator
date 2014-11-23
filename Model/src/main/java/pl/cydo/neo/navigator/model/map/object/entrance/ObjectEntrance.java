package pl.cydo.neo.navigator.model.map.object.entrance;

import pl.cydo.neo.navigator.model.utils.Time;

import java.util.List;

public  class ObjectEntrance {
    private List<ObjectEntranceAvailability> pointEntranceAvailabilities;
    private Time openFrom;
    private Time openTo;

    public List<ObjectEntranceAvailability> getPointEntranceAvailabilities() {
        return pointEntranceAvailabilities;
    }

    public void setPointEntranceAvailabilities(List<ObjectEntranceAvailability> pointEntranceAvailabilities) {
        this.pointEntranceAvailabilities = pointEntranceAvailabilities;
    }

    public Time getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(Time openFrom) {
        this.openFrom = openFrom;
    }

    public Time getOpenTo() {
        return openTo;
    }

    public void setOpenTo(Time openTo) {
        this.openTo = openTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectEntrance that = (ObjectEntrance) o;

        if (openFrom != null ? !openFrom.equals(that.openFrom) : that.openFrom != null) return false;
        if (openTo != null ? !openTo.equals(that.openTo) : that.openTo != null) return false;
        if (pointEntranceAvailabilities != null ? !pointEntranceAvailabilities.equals(that.pointEntranceAvailabilities) : that.pointEntranceAvailabilities != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pointEntranceAvailabilities != null ? pointEntranceAvailabilities.hashCode() : 0;
        result = 31 * result + (openFrom != null ? openFrom.hashCode() : 0);
        result = 31 * result + (openTo != null ? openTo.hashCode() : 0);
        return result;
    }
}
