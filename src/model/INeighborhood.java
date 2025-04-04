package model;

public interface INeighborhood {
    String getName();
    String getDescription();
    double getAverageRent();
    double getCrimeRate();
    double getSchoolRating();
    int getGreenSpaceScore();
    double getLivabilityScore();
    void setName(String name);
    void setDescription(String description);
    void setAverageRent(double averageRent);
    void setCrimeRate(double crimeRate);
    void setSchoolRating(double schoolRating);
    void setGreenSpaceScore(int greenSpaceScore);
    void setLivabilityScore(double livabilityScore);
}