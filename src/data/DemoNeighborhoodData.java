package data;

import model.Neighborhood;
import java.util.List;
import java.util.ArrayList;

public class DemoNeighborhoodData {

    public static List<Neighborhood> getDemoNeighborhoods() {
        List<Neighborhood> list = new ArrayList<>();

        list.add(new Neighborhood("Allston", 2200, 3.5, 7.2, 75));
        list.add(new Neighborhood("Back Bay", 3200, 2.1, 8.7, 82));
        list.add(new Neighborhood("Dorchester", 1800, 5.2, 6.0, 68));
        list.add(new Neighborhood("Beacon Hill", 3500, 1.9, 9.1, 79));
        list.add(new Neighborhood("Jamaica Plain", 2400, 2.8, 8.0, 90));
        list.add(new Neighborhood("Fenway", 2700, 3.0, 8.3, 88));

        return list;
    }
}