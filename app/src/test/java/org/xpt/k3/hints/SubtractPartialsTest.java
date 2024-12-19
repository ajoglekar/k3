package org.xpt.k3.hints;

import org.junit.jupiter.api.Test;
import org.xpt.k3.Grid;
import org.xpt.k3.Location;
import org.xpt.k3.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SubtractPartialsTest {

    @Test
    public void assertGenerationOfValidPartials() {
        final Hint subtract = new Subtract(2, makeRegion());
        PartialsGenerator target = new PartialsGenerator();

        List<Grid> grids = target.generatePartials(4, subtract);

        printPartials(grids);
        assertEquals(4,  grids.size());
    }

    private static List<Location> makeRegion() {
        LocationRepository repository = new LocationRepository(4);
        final List<Location> region = new ArrayList<>();
        region.add(repository.getLocation(1, 2));
        region.add(repository.getLocation(2, 2));
        return region;
    }

    static void printPartials(List<Grid> grids) {
        int index = 0;
        for (Grid grid : grids) {
            System.out.println("\n\n Partial: " + ++index + " ->");
            grid.print();
        }
        System.out.println();
    }

}
