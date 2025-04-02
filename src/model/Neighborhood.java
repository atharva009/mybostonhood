package model;

public class Neighborhood implements INeighborhood {

	private String name;
    private double averageRent;
    private double crimeRate;
    private double schoolRating;
    private int greenSpaceScore;
    private double livabilityScore;

    public Neighborhood(String name, double averageRent, double crimeRate,
                        double schoolRating, int greenSpaceScore) {
        this.name = name;
        this.averageRent = averageRent;
        this.crimeRate = crimeRate;
        this.schoolRating = schoolRating;
        this.greenSpaceScore = greenSpaceScore;
        this.livabilityScore = 0.0; // initially uncalculated
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getAverageRent() {
        return averageRent;
    }

    @Override
    public double getCrimeRate() {
        return crimeRate;
    }

    @Override
    public double getSchoolRating() {
        return schoolRating;
    }

    @Override
    public int getGreenSpaceScore() {
        return greenSpaceScore;
    }

    @Override
    public double getLivabilityScore() {
        return livabilityScore;
    }

    @Override
    public String toString() {
        return String.format(
            "%s | Rent: $%.2f | Crime: %.2f | School: %.1f | Green: %d | Score: %.2f",
            name, averageRent, crimeRate, schoolRating, greenSpaceScore, livabilityScore);
    }

}
