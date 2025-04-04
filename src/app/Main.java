package app;

import java.util.List;

import data.DemoNeighborhoodData;
import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import ui.LandingPage;

public class Main {

	public static NeighborhoodDirectory neighborhoodDirectory = new NeighborhoodDirectory();

    public static void main(String[] args) {
        // Load demo data
        List<Neighborhood> demoData = DemoNeighborhoodData.getDemoNeighborhoods();
        for (Neighborhood n : demoData) {
            neighborhoodDirectory.addNeighborhood(n.getName(), n);
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LandingPage(neighborhoodDirectory);
        });
    }
}
