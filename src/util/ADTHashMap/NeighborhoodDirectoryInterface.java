package util.ADTHashMap;

import java.util.List;
import model.Neighborhood;

public interface NeighborhoodDirectoryInterface {
	
		/** Adds a neighborhood to the directory.
		 * @param The name of the neighborhood. 
		 * @param The object of neighborhood which contains average rent, crime rate, schooling etc.*/
		public void addNeighborhood(String name, Neighborhood neighborhood);
		
		/** Get all details of a neighborhood.
		 * @param The object at the top of the stack.*/
	    public Neighborhood getNeighborhood(String name);
	    
	    /** Checks if a neighborhood exists.
		 * @param The name of the neighborhood. .*/
	    public boolean containsNeighborhood(String name);
	    
	    /** removes a neighborhood.
		 * @param The name of the neighborhood.*/
	    public void removeNeighborhood(String name);
	    
	    /** Displays all the neighborhoods.*/
	    public List<String> getAllNeighborhoodNames();
	}


