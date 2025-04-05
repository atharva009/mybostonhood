package util.ADTCompare;

public interface CompareListInterface<T> extends Iterable<T> {
    boolean add(T item);
    boolean remove(T item);
    boolean contains(T item);
    int size();
    void clear();
    T[] getAll(); // Return array instead of List
}
