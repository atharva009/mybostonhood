package model;

public interface INeighborhood {
    String getName();
    String getDescription();
    double getAverageRent();
    double getCrimeRate();
    double getSchoolRating();
    int getGreenSpaceScore();
    double getLivabilityScore();
}