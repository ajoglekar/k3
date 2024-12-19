package org.xpt.k3.hints;

import org.junit.jupiter.api.Test;
import org.xpt.k3.Grid;
import org.xpt.k3.Location;
import org.xpt.k3.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.xpt.k3.hints.SubtractPartialsTest.printPartials;


class ConstantPartialsTest {

    @Test
    public void assertGenerationOfValidPartials() {
        final Hint constant = new Constant(2, makeRegion());
        PartialsGenerator target = new PartialsGenerator();

        List<Grid> grids = target.generatePartials(4, constant);

        assertEquals(1,  grids.size());
        Grid grid1 = grids.get(0);
        Location firstLocation = constant.getRegion().get(0);
        assertEquals(2, grid1.get(firstLocation));

        printPartials(grids);
    }

    private static ArrayList<Location> makeRegion() {
        final ArrayList<Location> region = new ArrayList<>();
        region.add(new LocationRepository(4).getLocation(1, 3));
        return region;
    }

}
