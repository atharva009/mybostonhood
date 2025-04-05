package util.ADTScore;

import model.Neighborhood;

public interface ScoringAlgorithm {
    double calculateScore(Neighborhood n);
    String explainScore(Neighborhood n);
}
