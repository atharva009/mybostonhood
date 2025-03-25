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
    public void calculateLivabilityScore(double rentWeight,
                                         double crimeWeight,
                                         double schoolWeight,
                                         double greenWeight) {
        // Normalize rent and crime so that lower = better
        double normalizedRent = 1 / (averageRent + 1);      // Avoid division by zero
        double normalizedCrime = 1 / (crimeRate + 1);

        livabilityScore = (normalizedRent * rentWeight) +
                          (normalizedCrime * crimeWeight) +
                          (schoolRating * schoolWeight) +
                          (greenSpaceScore * greenWeight);
    }

    @Override
    public String toString() {
        return String.format(
            "%s | Rent: $%.2f | Crime: %.2f | School: %.1f | Green: %d | Score: %.2f",
            name, averageRent, crimeRate, schoolRating, greenSpaceScore, livabilityScore);
    }

}
