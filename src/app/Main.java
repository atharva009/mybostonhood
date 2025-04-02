package app;

import ui.LandingPage; 

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LandingPage(); 
        });
    }
}
