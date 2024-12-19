package org.xpt.k3.hints;

import org.xpt.k3.Grid;
import org.xpt.k3.Location;

import java.util.List;


public class Subtract implements Hint {

    private final int target;
    private final List<Location> region;

    public Subtract(int target, List<Location> region) {
        this.target = target;
        this.region = region;
    }

    @Override
    public List<Location> getRegion() {
        return region;
    }

    @Override
    public boolean verify(Grid candidate) {
        List<Location> locations = this.region;
        if (locations.size() != 2) return false;

        int value1 = candidate.get(locations.get(0));
        int value2 = candidate.get(locations.get(1));
        return (value1 - value2) == target  ||
                (value2 - value1) == target ;
    }

}
