package org.xpt.k3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

    private final int order;
    private final Map<Location, Integer> values;
    private final boolean[][] usedInRows, usedInColumns;

    public Grid(int order) {
        this.order = order;
        this.values = new HashMap<>();
        this.usedInRows = new boolean[order + 1][order + 1];
        this.usedInColumns = new boolean[order + 1][order + 1];
    }

    public boolean set(Location location, int value) {
        if (!usedInRows[location.row()][value] && !usedInColumns[location.column()][value]) {
            usedInRows[location.row()][value] = true;
            usedInColumns[location.column()][value] = true;
            values.put(location, value);
            return true;
        } else {
            return false;
        }
    }

    public void reset(final Location location, final int value) {
        usedInRows[location.row()][value] = false;
        usedInColumns[location.column()][value] = false;
        values.remove(location);
    }

    public void print() {
        ArrayList<Location> locations = new ArrayList<>(values.keySet());
        locations.sort(Comparator.comparingInt(Location::index));
        int oldRow = 0, oldColumn = 0;
        for (Location location : locations) {
            int rowDiff = location.row() - oldRow;
            for ( int i = 0; i < rowDiff; i++) {
                System.out.println();
            }
            if (rowDiff > 0) {
                oldColumn = 0;
            }
            int colDiff = location.column() - oldColumn;
            for ( int i = 0; i < colDiff; i++) {
                System.out.print("  ");
            }
            oldRow = location.row();
            oldColumn = location.column();

            System.out.print(values.get(location));
        }
    }

    public int get(Location location) {
        return values.get(location);
    }

    public Grid clone() {
        Grid cloned = new Grid(this.order);
        for (Map.Entry<Location, Integer> entry: this.values.entrySet()) {
            cloned.set(entry.getKey(), entry.getValue());
        }

        return cloned;
    }

    // generation candidate vals for location (all...)
    public List<Integer> candidates() {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= order; i++) {
            result.add(i);
        }
        return result;
    }

    public Grid merge(Grid other) {
        boolean success = true;
        Grid current = other.clone();
        List<Map.Entry<Location, Integer>> entries = new ArrayList<>(values.entrySet());
        for (int i = 0; success && i < entries.size(); i++) {
            Map.Entry<Location, Integer> entry = entries.get(i);
            success = current.set(entry.getKey(), entry.getValue());
        }
        if (success) {
            return current;
        } else {
            return new Grid(order);
        }
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid grid)) return false;

        return order == grid.order && values.equals(grid.values) && Arrays.deepEquals(usedInRows, grid.usedInRows) && Arrays.deepEquals(usedInColumns, grid.usedInColumns);
    }

    @Override
    public int hashCode() {
        int result = order;
        result = 31 * result + values.hashCode();
        result = 31 * result + Arrays.deepHashCode(usedInRows);
        result = 31 * result + Arrays.deepHashCode(usedInColumns);
        return result;
    }
}
