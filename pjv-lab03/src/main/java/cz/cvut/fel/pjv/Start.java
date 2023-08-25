package cz.cvut.fel.pjv;

/**
 * @author Jirka Sebek
 */
public class Start {

    public static void main(String[] args) {

         
        /* homework */
        Queue queue = new CircularArrayQueue(5);

        System.out.println(queue.isEmpty());
        queue.enqueue("Starkiller");
        queue.enqueue("C-3PO");
        queue.enqueue("Jabba the Hutt");
        queue.enqueue("HK-47");
        queue.enqueue("Darth Nihilus");
        queue.enqueue("Count Dooku");
        System.out.println("size: " + queue.size());
        System.out.println(queue.isFull());
        System.out.println("Value dequeued from CircularArrayQueue: " + queue.dequeue());
        System.out.println("printing all elements: ");
        queue.printAllElements();
        queue.enqueue("Jango Fett");
        System.out.println("size: " + queue.size());
    }
}
