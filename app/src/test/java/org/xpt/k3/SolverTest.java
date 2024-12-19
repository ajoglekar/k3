package org.xpt.k3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolverTest {

    @Test
    public void assertValidSolution4 () throws IOException {
        long start = System.currentTimeMillis();
        List<Grid> solutions = new Solver().solve("src/test/resources/sample-2.json");
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " milliseconds.");

        System.out.println("# of solutions = " + solutions.size());
        assertEquals(1, solutions.size());

        Grid expected = buildExpected4(new LocationRepository(4));
        System.out.println("\nexpected solution: ");
        expected.print();

        Grid actual = solutions.get(0);
        System.out.println("\nactual solution: ");
        actual.print();
        System.out.println();

        assertEquals(expected, actual);
    }

    private Grid buildExpected4(LocationRepository repository) {
        int[] values = {1, 3, 2, 4,
                3, 4, 1, 2,
                4, 2, 3, 1,
                2, 1, 4, 3};
        Grid expected = new Grid(repository.getOrder());
        for (int idx = 0; idx < values.length; idx++) {
            expected.set(repository.getLocation(idx), values[idx]);
        }
        return expected;
    }

    @Test
    public void assertValidSolution7 () throws IOException {
        long start = System.currentTimeMillis();
        List<Grid> solutions = new Solver().solve("src/test/resources/sample-3-7x7.json");
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " milliseconds.");

        System.out.println("# of solutions = " + solutions.size());
        assertEquals(1, solutions.size());

        Grid expected = buildExpected7x7(new LocationRepository(7));
        System.out.println("\nexpected solution: ");
        expected.print();

        Grid actual = solutions.get(0);
        System.out.println("\nactual solution: ");
        actual.print();
        System.out.println();

        assertEquals(expected, actual);
    }

    private Grid buildExpected7x7(LocationRepository repository) {
        int[] values = {5, 7, 6, 4, 3, 2, 1,
                6, 2, 1, 3, 4, 7, 5,
                4, 6, 5, 2, 7, 1, 3,
                2, 5, 3, 1, 6, 4, 7,
                1, 3, 2, 7, 5, 6, 4,
                7, 1, 4, 5, 2, 3, 6,
                3, 4, 7, 6, 1, 5, 2};
        Grid expected = new Grid(7);
        for (int idx = 0; idx < values.length; idx++) {
            expected.set(repository.getLocation(idx), values[idx]);
        }
        return expected;
    }

}
