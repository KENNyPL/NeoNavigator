package pl.cydo.neo.navigator.web.model;

import pl.cydo.neo.navigator.model.map.service.ServicePoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PointsTableData {
    List<PointsTableDataRow> data;

    public PointsTableData(Collection<ServicePoint> servicePoints) {
        data = new ArrayList<>(servicePoints.size());
        for(ServicePoint point : servicePoints){
            data.add(new PointsTableDataRow(point.getName(), point.getLongitude().toString(), point.getLatitude().toString()));
        }
    }

    public List<PointsTableDataRow> getData() {
        return data;
    }

    public void setData(List<PointsTableDataRow> data) {
        this.data = data;
    }
}
