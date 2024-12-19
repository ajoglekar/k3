package org.xpt.k3.hints;

import org.junit.jupiter.api.Test;
import org.xpt.k3.Grid;
import org.xpt.k3.Location;
import org.xpt.k3.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xpt.k3.hints.SubtractPartialsTest.printPartials;


class DividePartialsTest {

    @Test
    public void assertGenerationOfValidPartials() {
        final ArrayList<Location> region = makeRegion();
        final Hint divide = new Divide(2, region);

        PartialsGenerator target = new PartialsGenerator();

        List<Grid> grids = target.generatePartials(4, divide);

        printPartials(grids);
        assertEquals(4,  grids.size());
    }

    private static ArrayList<Location> makeRegion() {
        LocationRepository repository = new LocationRepository(4);
        final ArrayList<Location> region = new ArrayList<>();
        region.add(repository.getLocation(0, 2));
        region.add(repository.getLocation(0, 3));
        return region;
    }

}
