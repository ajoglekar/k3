package org.xpt.k3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.xpt.k3.hints.Add;
import org.xpt.k3.hints.Constant;
import org.xpt.k3.hints.Divide;
import org.xpt.k3.hints.Hint;
import org.xpt.k3.hints.Multiply;
import org.xpt.k3.hints.PartialsGenerator;
import org.xpt.k3.hints.Subtract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solver {

    public List<Grid> solve(String pathToPuzzle) throws IOException {
        final Map<String, Object> specification = extractSpecification(pathToPuzzle);
        final int order = Integer.parseInt(specification.get("order").toString());
        Map<Hint, List<Grid>> partials =
                buildPartials(order,
                        buildHints(new LocationRepository(order),
                                (List<Map<String, Object>>) specification.get("hints")));

        return resolvePartials(order, partials);
    }

    Map<String, Object> extractSpecification(String pathToPuzzle) throws IOException {
        Path path = Paths.get(pathToPuzzle);
        String input = Files.readString(path);

        return new ObjectMapper().readValue(input, HashMap.class);
    }

    List<Hint> buildHints(LocationRepository repository, List<Map<String, Object>> specs) {
        List<Hint> hints = new ArrayList<>();
        for(Map<String, Object> spec: specs) {
            buildHint(repository, spec, hints);
        }
        return hints;
    }

    private static void buildHint(LocationRepository repository, Map<String, Object> spec, List<Hint> collector) {
        final int target = Integer.parseInt(spec.get("target").toString());
        final List<Integer> region = (List<Integer>) spec.get("region");
        List<Location> locations = new ArrayList<>();
        for (int index : region) {
            locations.add(repository.getLocation(index));
        }
        final String operation = spec.get("operation").toString();
        if ("Plus".equals(operation)) {
            collector.add(new Add(target, locations));
        } else if ("Times".equals(operation)) {
            collector.add(new Multiply(target, locations));
        } else if ("Divide".equals(operation)) {
            collector.add(new Divide(target, locations));
        } else if ("Minus".equals(operation)) {
            collector.add(new Subtract(target, locations));
        } else if ("Constant".equals(operation)) {
            collector.add(new Constant(target, locations));
        } else {
            System.out.println("Error: Unknown operation!");
        }
    }

    Map<Hint, List<Grid>> buildPartials(int order, List<Hint> hints) {
        PartialsGenerator generator = new PartialsGenerator();
        HashMap<Hint, List<Grid>> result = new HashMap<>();
        for (Hint hint : hints) {
            result.put(hint, generator.generatePartials(order, hint));
        }
        return result;
    }

    List<Grid> resolvePartials(int order, Map<Hint, List<Grid>> partialSets) {
        List<Grid> solutions = new ArrayList<>();
        final List<List<Grid>> formulaSets = new ArrayList<>(partialSets.values());
        formulaSets.sort(Comparator.comparingInt(List::size)); // process smallest sets of partials first.
        Grid current = new Grid(order);
        merge(0, formulaSets, current, solutions);
        return solutions;
    }

    // traverse partials within a formula, when successful, call back for next formula
    void merge(int f, List<List<Grid>> fSets, Grid current, List<Grid> collector) {
        if (f < fSets.size()) {
            final List<Grid> partials = fSets.get(f);
            for (Grid partial : partials) {
                final Grid next = partial.merge(current);
                if (!next.isEmpty()) { // merged successfully
                    if (f == (fSets.size() - 1)) { // final formula merged
                        collector.add(next); // capture a valid solution
                    } else {
                        merge((f + 1), fSets, next, collector); // try merging partials for the next hint
                    }
                }
            }
        }
    }

}
