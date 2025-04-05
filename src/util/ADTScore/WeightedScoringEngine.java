package util.ADTScore;

import model.Neighborhood;

public class WeightedScoringEngine implements ScoringAlgorithm {

    @Override
    public double calculateScore(Neighborhood n) {
        double score = 0.0;

        // Weighted formula
        score += normalize(n.getSchoolRating(), 0, 10) * 0.3;
        score += normalize(n.getGreenSpaceScore(), 0, 100) * 0.2;
        score += (1 - normalize(n.getAverageRent(), 500, 5000)) * 0.25;
        score += (1 - normalize(n.getCrimeRate(), 0, 100)) * 0.25;

        double finalScore = Math.round(score * 100.0) / 100.0;
        n.setLivabilityScore(finalScore);
        return finalScore;
    }

    @Override
    public String explainScore(Neighborhood n) {
        return String.format(
            "%s:\n" +
            "- School Rating: %.1f (30%% weight)\n" +
            "- Green Space Score: %d (20%% weight)\n" +
            "- Avg. Rent: $%.2f (lower is better, 25%%)\n" +
            "- Crime Rate: %.2f (lower is better, 25%%)\n" +
            "→ Final Livability Score: %.2f\n",
            n.getName(),
            n.getSchoolRating(),
            n.getGreenSpaceScore(),
            n.getAverageRent(),
            n.getCrimeRate(),
            n.getLivabilityScore()
        );
    }

    private double normalize(double value, double min, double max) {
        if (value < min) return 0;
        if (value > max) return 1;
        return (value - min) / (max - min);
    }
}
