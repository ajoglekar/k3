package org.xpt.k3;


import java.util.HashMap;
import java.util.Map;


public class LocationRepository {

    private final int order;
    private final Map<Integer, Location> locations;

    public LocationRepository(int order) {
        this.order = order;
        this.locations = new HashMap<>();
    }

    public Location getLocation(int row, int column) {
        final int index = ( row * order ) + column;
        if (!locations.containsKey(index)) {
            locations.put(index, new Location(row, column, index));
        }
        return locations.get(index);
    }

    public Location getLocation(int index) {
        if (!locations.containsKey(index)) {
            int[] rc = calculateRowColumn(index);
            locations.put(index, new Location(rc[0], rc[1], index));
        }
        return locations.get(index);
    }

    public int getOrder() {
        return order;
    }

    public int calculateIndex(int row, int column) {
        return ( row * order ) + column;
    }

    int[] calculateRowColumn(int index) {
        int[] result = new int[2];
        result[0] = (index / order );
        result[1] = (index % order );
        return result;
    }

}
