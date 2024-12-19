package org.xpt.k3.hints;

import org.junit.jupiter.api.Test;
import org.xpt.k3.Grid;
import org.xpt.k3.Location;
import org.xpt.k3.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xpt.k3.hints.SubtractPartialsTest.printPartials;


class AddPartialsTest {

    @Test
    public void assertGenerationOfValidPartials() {
        final Hint add = new Add(11, makeRegion());
        PartialsGenerator target = new PartialsGenerator();

        List<Grid> grids = target.generatePartials(4, add);

        assertEquals(12,  grids.size());
        printPartials(grids);
    }

    private static ArrayList<Location> makeRegion() {
        LocationRepository repository = new LocationRepository(4);
        final ArrayList<Location> region = new ArrayList<>();
        region.add(repository.getLocation(1, 0));
        region.add(repository.getLocation(2, 0));
        region.add(repository.getLocation(3, 0));
        region.add(repository.getLocation(2, 1));
        return region;
    }

}
