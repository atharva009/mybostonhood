package data;

import model.Neighborhood;
import java.util.List;
import java.util.ArrayList;

public class DemoNeighborhoodData {

    public static List<Neighborhood> getDemoNeighborhoods() {
        List<Neighborhood> list = new ArrayList<>();

        list.add(new Neighborhood("Allston", "A vibrant student neighborhood with good transit access.", 2200, 3.5, 7.2, 75));
        list.add(new Neighborhood("Back Bay","Upscale historic area with shops and brownstones.", 3200, 2.1, 8.7, 82));
        list.add(new Neighborhood("Dorchester", "Diverse and affordable, but higher crime rates.", 1800, 5.2, 6.0, 68));
        list.add(new Neighborhood("Beacon Hill", "Elite neighborhood with cobblestone streets.", 3500, 1.9, 9.1, 79));
        list.add(new Neighborhood("Jamaica Plain", "Community-centric and green, perfect for families.", 2400, 2.8, 8.0, 90));
        list.add(new Neighborhood("Fenway", "Lively area near universities and Fenway Park.", 2700, 3.0, 8.3, 88));


        return list;
    }
}