package util.ADTBST;

import model.Neighborhood;
import java.util.Comparator;

public enum SortField {
    NAME("Name", Comparator.comparing(Neighborhood::getName)),
    AVERAGE_RENT("Average Rent", Comparator.comparingDouble(Neighborhood::getAverageRent)),
    CRIME_RATE("Crime Rate", Comparator.comparingDouble(Neighborhood::getCrimeRate)),
    SCHOOL_RATING("School Rating", Comparator.comparingDouble(Neighborhood::getSchoolRating).reversed()),
    GREEN_SPACE("Green Space", Comparator.comparingInt(Neighborhood::getGreenSpaceScore).reversed()),
    LIVABILITY_SCORE("Livability Score", Comparator.comparingDouble(Neighborhood::getLivabilityScore).reversed());

    private final String displayName;
    private final Comparator<Neighborhood> comparator;

    SortField(String displayName, Comparator<Neighborhood> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    public Comparator<Neighborhood> getComparator() {
        return comparator;
    }

    @Override
    public String toString() {
        return displayName;
    }
}