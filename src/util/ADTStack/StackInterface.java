package util.ADTStack;

public interface StackInterface<T> {

    /** Pushes a new entry onto the top of this stack. */
    public void push(T newEntry);

    /** Removes and returns this stack's top entry.
     * @return The object at the top of the stack. */
    public T pop();

    /** Retrieves this stack's top entry without removing it.
     * @return The object at the top of the stack. */
    public T peek();

    /** Detects whether this stack is empty.
     * @return True if the stack is empty. */
    public boolean isEmpty();

    /** Removes all entries from this stack. */
    public void clear();
}
