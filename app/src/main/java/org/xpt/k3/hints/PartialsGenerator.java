package org.xpt.k3.hints;

import org.xpt.k3.Grid;
import org.xpt.k3.Location;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PartialsGenerator {

    public List<Grid> generatePartials(int order, Hint hint) {
        ArrayList<Grid> partials = new ArrayList<>();
        List<Location> ordered = new ArrayList<>(hint.getRegion());
        ordered.sort(Comparator.comparingInt(Location::index));
        solve(0, ordered, new Grid(order), partials, hint);
        return partials;
    }

    private void solve(int index, List<Location> orderedLocations, Grid grid, ArrayList<Grid> partials, Hint hint) {
        final Location location = orderedLocations.get(index);
        for (int val : grid.candidates()) {
            if (grid.set(location, val)) {
                if (index == orderedLocations.size() - 1) {
                    if (hint.verify(grid)) {
                        partials.add(grid.clone());
                    }
                } else {
                    solve (index + 1, orderedLocations, grid, partials, hint);
                }
                grid.reset(location, val);
            }
        }
    }

}
