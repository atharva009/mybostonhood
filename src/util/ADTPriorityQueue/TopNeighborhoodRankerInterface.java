package util.ADTPriorityQueue;

import model.Neighborhood;


public interface TopNeighborhoodRankerInterface {
	public void addNeighborhood(Neighborhood neighborhood);
    public Neighborhood getTopNeighborhood();
    public Neighborhood removeTopNeighborhood();
    public boolean isEmpty();
    public int size();
}
