package org.xpt.k3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LocationRepositoryTest {

    @Test
    public void validateIndexCalculation() {
        LocationRepository target = new LocationRepository(4);

        validation(target, 0, 0, 0); // first location
        validation(target, 4, 1, 0); // first on second row
        validation(target, 7, 1, 3); // last on second row
        validation(target, 15, 3, 3); // last location
    }

    private static void validation(LocationRepository target, int index, int row, int column) {
        assertEquals(index, target.calculateIndex(row, column));
        int[] rc1 = target.calculateRowColumn(index);
        assertEquals(row, rc1[0]);
        assertEquals(column, rc1[1]);
    }

    @Test
    public void validateIndexCalculations2() {
        LocationRepository target = new LocationRepository(4);
        for (int r = 0; r < target.getOrder(); r++) {
            for (int c = 0; c < target.getOrder(); c++) {
                int idx = target.calculateIndex(r, c);
                int[] rc = target.calculateRowColumn(idx);
                System.out.print("original: row = " + r + ", column = " + c + ", index = " + idx + " ");
                System.out.println("calculated: row = " + rc[0] + ", column = " + rc[1] + " ");
            }
        }
    }

}
