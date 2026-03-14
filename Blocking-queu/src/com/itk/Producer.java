package com.itk;

public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            for (int i = 1; i <= 10; i++) {

                queue.enqueue(i);

                System.out.println("Produced: " + i);

                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
