package org.xpt.k3.hints;

import org.xpt.k3.Grid;
import org.xpt.k3.Location;

import java.util.List;


public interface Hint {

    List<Location> getRegion();

    boolean verify(Grid candidate);

}

