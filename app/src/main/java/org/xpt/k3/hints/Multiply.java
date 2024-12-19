package org.xpt.k3.hints;

import org.xpt.k3.Grid;
import org.xpt.k3.Location;

import java.util.List;


public class Multiply implements Hint {

    private final int target;
    private final List<Location> region;

    public Multiply(int target, List<Location> region) {
        this.target = target;
        this.region = region;
    }

    @Override
    public List<Location> getRegion() {
        return region;
    }

    @Override
    public boolean verify(Grid candidate) {
        int actual = 1;
        for (Location location: this.region) {
            actual *= candidate.get(location);
        }

        return target == actual;
    }

}
