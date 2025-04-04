package model;

public class Neighborhood implements INeighborhood {

	private String name;
	private String description;
    private double averageRent;
    private double crimeRate;
    private double schoolRating;
    private int greenSpaceScore;
    private double livabilityScore;

    public Neighborhood(String name, String description, double averageRent, double crimeRate,
                        double schoolRating, int greenSpaceScore) {
        this.name = name;
        this.description = description;
        this.averageRent = averageRent;
        this.crimeRate = crimeRate;
        this.schoolRating = schoolRating;
        this.greenSpaceScore = greenSpaceScore;
        this.livabilityScore = 0.0;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getDescription() {
		return description;
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
    
    @Override
    public void setName(String name) {
    	this.name = name;
    }
    
    @Override
    public void setDescription(String description) {
    	this.description = description;
    }
    
    
    @Override
    public void setAverageRent(double averageRent) {
        this.averageRent = averageRent;
    }

    @Override
    public void setCrimeRate(double crimeRate) {
        this.crimeRate = crimeRate;
    }

    @Override
    public void setSchoolRating(double schoolRating) {
        this.schoolRating = schoolRating;
    }

    @Override
    public void setGreenSpaceScore(int greenSpaceScore) {
        this.greenSpaceScore = greenSpaceScore;
    }

    @Override
    public void setLivabilityScore(double livabilityScore) {
        this.livabilityScore =livabilityScore;
    }

}
