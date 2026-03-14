package com.itk;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private Queue<T> queue;
    private int capacity;

    public BlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    // add element to queue
    public synchronized void enqueue(T item) throws InterruptedException {

        while (queue.size() == capacity) {
            wait(); // wait if queue is full
        }

        queue.add(item);
        notifyAll(); // notify waiting consumers
    }

    // remove element from queue
    public synchronized T dequeue() throws InterruptedException {

        while (queue.isEmpty()) {
            wait(); // wait if queue is empty
        }

        T item = queue.remove();
        notifyAll(); // notify waiting producers

        return item;
    }

    // return current size
    public synchronized int size() {
        return queue.size();
    }
}
