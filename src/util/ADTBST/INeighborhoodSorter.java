package util.ADTBST;

import java.util.List;

import model.Neighborhood;

public interface INeighborhoodSorter {
	
	/**
     * Inserts a neighborhood into the sorter based on the configured metric.
     * @param neighborhood the neighborhood to insert
     */
    void insertNeighborhood(Neighborhood neighborhood);

    /**
     * Retrieves a list of all neighborhoods in sorted order.
     * @return a sorted list of neighborhoods
     */
    List<Neighborhood> getSortedNeighborhoods();

    /**
     * Prints the sorted list of neighborhoods to the console.
     */
    void displaySortedNeighborhoods();
    
}
