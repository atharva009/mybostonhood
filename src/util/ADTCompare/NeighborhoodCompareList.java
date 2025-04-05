package util.ADTCompare;

import model.Neighborhood;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NeighborhoodCompareList implements CompareListInterface<Neighborhood> {

    private final int MAX_SIZE = 3;
    private Neighborhood[] items;
    private int count;

    public NeighborhoodCompareList() {
        items = new Neighborhood[MAX_SIZE];
        count = 0;
    }

    @Override
    public boolean add(Neighborhood item) {
        if (count >= MAX_SIZE || contains(item)) {
            return false;
        }
        items[count++] = item;
        return true;
    }

    @Override
    public boolean remove(Neighborhood item) {
        for (int i = 0; i < count; i++) {
            if (items[i].getName().equalsIgnoreCase(item.getName())) {
                // Shift elements left
                for (int j = i; j < count - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[--count] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Neighborhood item) {
        for (int i = 0; i < count; i++) {
            if (items[i].getName().equalsIgnoreCase(item.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        for (int i = 0; i < count; i++) {
            items[i] = null;
        }
        count = 0;
    }

    @Override
    public Neighborhood[] getAll() {
        Neighborhood[] result = new Neighborhood[count];
        System.arraycopy(items, 0, result, 0, count);
        return result;
    }

    @Override
    public Iterator<Neighborhood> iterator() {
        return new Iterator<Neighborhood>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < count;
            }

            @Override
            public Neighborhood next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items[current++];
            }
        };
    }
}
