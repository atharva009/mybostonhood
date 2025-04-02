package util.ADTHashMap;

import java.util.List;
import java.util.ArrayList;

import model.Neighborhood;

public class NeighborhoodDirectory implements NeighborhoodDirectoryInterface {
	
	private static final int INITIAL_CAPACITY = 16; // Default bucket size
    private Street[] streets;
    private int size;
    
    // Entry class to hold key-value pairs
    private static class Entry {
        String key;
        Neighborhood value;
        Entry next; // Linked list for collision handling

        Entry(String key, Neighborhood value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    // Street class to store the linked list of entries
    private static class Street {
        Entry head;
    }
    
    //Default constructor Initialization
	public NeighborhoodDirectory() {
		this.streets = new Street[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
        	streets[i] = new Street();
        }
        this.size = 0;
	}
	
	// Hash function to get index from key
    private int getStreetIndex(String key) {
        return Math.abs(key.hashCode()) % streets.length;
    }
	
    // Adds a neighborhood to the directory 
	public void addNeighborhood(String name, Neighborhood neighborhood) {
		int index = getStreetIndex(name);
        Entry newEntry = new Entry(name, neighborhood);
        
        Entry current = streets[index].head;
        if (current == null) {
        	streets[index].head = newEntry;
            size++;
            return;
        }
        
        // Check if key exists, update value if found
        while (current.next != null) {
            if (current.key.equals(name)) {
                current.value = neighborhood; // Update value
                return;
            }
            current = current.next;
        }

        if (current.key.equals(name)) {
            current.value = neighborhood; // Update last node if key matches
        } else {
            current.next = newEntry; // Append new entry
            size++;
        }
	}
    
	// Retrieves a neighborhood by name
    public Neighborhood getNeighborhood(String name) {
    	int index = getStreetIndex(name);
        Entry current = streets[index].head;

        while (current != null) {
            if (current.key.equals(name)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Not found
    }
    
    // Checks if a neighborhood exists
    public boolean containsNeighborhood(String name){
    	return getNeighborhood(name) != null;
    }

    //Removes a neighborhood by name
    public void removeNeighborhood(String name) {
    	int index = getStreetIndex(name);
        Entry current = streets[index].head;
        Entry prev = null;

        while (current != null) {
            if (current.key.equals(name)) {
                if (prev == null) {
                    streets[index].head = current.next; // Remove head node
                } else {
                    prev.next = current.next; // Remove middle or last node
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    
    //Retrieves all neighborhood names
    public List<String> getAllNeighborhoodNames() {
    	List<String> names = new ArrayList<>();
        
        for (Street street : streets) {
            if (street == null) continue; // Skip empty streets (buckets)
            
            Entry current = street.head;
            while (current != null) {
                names.add(current.key);
                current = current.next;
            }
        }
        
        return names;
    }

}
