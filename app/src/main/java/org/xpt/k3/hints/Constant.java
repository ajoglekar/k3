package org.xpt.k3.hints;

import org.xpt.k3.Grid;
import org.xpt.k3.Location;

import java.util.List;


public class Constant implements Hint {

    final int target;
    final List<Location> region;

    public Constant(int target, List<Location> region) {
        this.target = target;
        this.region = region;
    }

    @Override
    public List<Location> getRegion() {
        return region;
    }

    @Override
    public boolean verify(Grid candidate) {
        Location location = this.region.get(0);// has only one location
        return candidate.get(location) == target;
    }

}
