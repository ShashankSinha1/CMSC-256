package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack<T> implements cmsc256.StackInterface<T> {

    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;


    public ArrayBasedStack(){
        clear();
    }

    public ArrayBasedStack(int capacity){

        if(capacity<0)
            throw new IllegalArgumentException("Array Initial Size error");

        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[capacity];

        data = tempStack;

        topOfStack = -1;
    }

    /** method: expandArray()
     * doubles the size of the array when called upon
     */

    private void expandArray(){

        int capacity = data.length * 2;
        data = Arrays.copyOf(data, capacity);

    }

    /** method isArrayFull()
     * returns true if the topOfStack exceeds the largest index in the array
     *
     */

    private boolean isArrayFull(){
        int size = data.length -1;
        if(topOfStack >= size){
            return true;
        }
        return false;
    }

    /** Adds a new entry to the top of this stack.
     @param newEntry  An object to be added to the stack. */
    public void push(T newEntry){
        if(isArrayFull() == true){
            expandArray();
        }

        topOfStack++;
        data[topOfStack] = newEntry;

    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    public T pop(){
        if(isEmpty() == true){
            throw new EmptyStackException();
        }
        T temp = data[topOfStack];
        data[topOfStack] = null;
        topOfStack--;
        return temp;
    }

    /** Retrieves this stack's top entry.
     @return  The object at the top of the stack.
     @throws  EmptyStackException if the stack is empty. */
    public T peek(){
        if(isEmpty() == true){
            throw new EmptyStackException();
        }
        return data[topOfStack];
    }

    /** Detects whether this stack is empty.
     @return  True if the stack is empty. */
    public boolean isEmpty(){
        if(topOfStack < 0){
            return true;
        }
        else
            return false;
    }

    /** Removes all entries from this stack. */
    public void clear() {

        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[INITIAL_CAPACITY];

        data = tempStack;

        topOfStack = -1;

    }
}
