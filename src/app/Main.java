package app;

import model.Neighborhood;
import util.ADTHashMap.NeighborhoodDirectory;
import ui.LandingPage;

public class Main {

    // Global shared directory for neighborhoods
    public static NeighborhoodDirectory neighborhoodDirectory = new NeighborhoodDirectory();

    public static void main(String[] args) {
        // Launch the Swing UI in the event dispatch thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LandingPage();
        });
    }
}
