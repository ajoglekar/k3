package org.xpt.k3.hints;

import org.junit.jupiter.api.Test;
import org.xpt.k3.Grid;
import org.xpt.k3.Location;
import org.xpt.k3.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xpt.k3.hints.SubtractPartialsTest.printPartials;


class MultiplyPartialsTest {

    @Test
    public void assertGenerationOfValidPartials() {
        final Hint multiply = new Multiply(12, makeRegion());
        PartialsGenerator target = new PartialsGenerator();

        List<Grid> grids = target.generatePartials(4, multiply);

        printPartials(grids);
        assertEquals(7,  grids.size());
    }

    private static List<Location> makeRegion() {
        LocationRepository repository = new LocationRepository(4);
        final List<Location> region = new ArrayList<>();
        region.add(repository.getLocation(0, 0));
        region.add(repository.getLocation(0, 1));
        region.add(repository.getLocation(1, 1));
        return region;
    }

}
