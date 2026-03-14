package com.itk;

import java.util.concurrent.*;
import java.util.*;

public class ComplexTaskExecutor {

    private int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks(int numberOfTasks) {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);

        List<Integer> results = Collections.synchronizedList(new ArrayList<>());

        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {

            int sum = 0;

            for (int r : results) {
                sum += r;
            }

            System.out.println("All tasks completed. Combined Result = " + sum);
        });

        for (int i = 1; i <= numberOfTasks; i++) {

            int taskId = i;

            executor.submit(() -> {

                try {

                    ComplexTask task = new ComplexTask(taskId);

                    int result = task.execute();

                    results.add(result);

                    System.out.println(Thread.currentThread().getName()
                            + " waiting at barrier");

                    barrier.await();

                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }

            });
        }

        executor.shutdown();
    }
}
