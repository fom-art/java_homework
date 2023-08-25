package cz.cvut.fel.pjv;

/**
 * Implementation of the {@link Queue} backed by fixed size array.
 */
public class CircularArrayQueue implements Queue {

    private final int CONST_DEFAULT_CAPACITY = 5;
    private String[] queue;
    private int start = 0;
    private int end = 0;

    /**
     * Creates the queue with capacity set to the value of 5.
     */
    public CircularArrayQueue() {
        setQueueCapacity(CONST_DEFAULT_CAPACITY);
    }

    /**
     * Creates the queue with given {@code capacity}. The capacity represents maximal number of elements that the
     * queue is able to store.
     *
     * @param capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        setQueueCapacity(capacity);
    }

    private void setQueueCapacity(int capacity) {
        this.queue = new String[capacity + 1];
    }

    private int getQueueCapacity() {
        return this.queue.length;
    }

    @Override
    public int size() {
        int result = 0;
        for (int i = this.start; i != this.end; i = getNextQueueIndex(i)) {
            result++;
        }
        return result;
    }

    private int getNextQueueIndex(int previousIndex) {
        if (previousIndex == (getQueueCapacity() - 1)) {
            return 0;
        } else {
            return previousIndex + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.start == this.end;
    }

    @Override
    public boolean isFull() {
        return start == getNextQueueIndex(this.end);
    }

    @Override
    public boolean enqueue(String obj) {
        if (isFull()) {
            return false;
        } else {
            //i will rather showing the next element index
            this.queue[this.end] = obj;
            this.end = getNextQueueIndex(this.end);
            return true;
        }
    }

    @Override
    public String dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            String readValue = this.queue[this.start];
            this.queue[this.start] = "";
            this.start = getNextQueueIndex(this.start);
            return readValue;
        }
    }

    @Override
    public void printAllElements() {
        for (String element : this.queue) {
            if (element != null){
                System.out.println(element);
            }
        }
    }
}
