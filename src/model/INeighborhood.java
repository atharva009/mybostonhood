package model;

public interface INeighborhood {
    String getName();
    double getAverageRent();
    double getCrimeRate();
    double getSchoolRating();
    int getGreenSpaceScore();
    double getLivabilityScore();

    void calculateLivabilityScore(double rentWeight,
                                   double crimeWeight,
                                   double schoolWeight,
                                   double greenWeight);
}