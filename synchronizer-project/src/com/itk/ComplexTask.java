package com.itk;

public class ComplexTask {

    private int taskId;

    public ComplexTask(int taskId) {
        this.taskId = taskId;
    }

    public int execute() {

        int result = 0;

        try {

            System.out.println(Thread.currentThread().getName()
                    + " executing task " + taskId);

            // simulate complex work
            Thread.sleep(1000);

            result = taskId * 10;

            System.out.println(Thread.currentThread().getName()
                    + " finished task " + taskId + " result = " + result);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return result;
    }
}