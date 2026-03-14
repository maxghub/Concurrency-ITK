# Concurrency-ITK

# Blocking-queu#
Practical Task – Concurrency – Blocking Queue
Assume you have a thread pool and you want to implement a blocking queue for transferring tasks between threads. Create a BlockingQueue class that provides safe addition and retrieval of elements between producers and consumers in the context of a thread pool.

The BlockingQueue class should contain the methods enqueue() for adding an element to the queue and dequeue() for retrieving an element. If the queue is empty, dequeue() must block the calling thread until a new element appears.

The queue must have a fixed size.

Use the wait() and notify() mechanisms to coordinate between producers and consumers. Implement a size() method that returns the current size of the queue.


# synchronizer-project
Practical Task – Concurrency – Synchronizers
Thread synchronization using CyclicBarrier and ExecutorService

In this task, we will use CyclicBarrier and ExecutorService to synchronize multiple threads performing a complex task and then waiting until all threads complete execution in order to combine their results.

Create a ComplexTask class representing a complex task that multiple threads will execute. Each task should implement an execute() method that performs part of the complex task.

Create a ComplexTaskExecutor class that uses CyclicBarrier and ExecutorService to synchronize task execution. Implement the executeTasks(int numberOfTasks) method, which creates a thread pool and assigns an instance of the complex task to each thread for execution. Then use CyclicBarrier to wait for all threads to complete and combine the results of their work. In the main method, create an instance of ComplexTaskExecutor and call the executeTasks method with several tasks to execute.

Testing code

public class TestComplexTaskExecutor {

    public static void main(String[] args) {
        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5); // Number of tasks to execute

        Runnable testRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " started the test.");

            // Execute tasks
            taskExecutor.executeTasks(5);

            System.out.println(Thread.currentThread().getName() + " completed the test.");
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



# concurrent-bank-project
Practical Task – Concurrency – Multithreaded Bank Account
In the virtual bank "ConcurrentBank", it was decided to introduce multithreading to process customer account operations. The system must support concurrent deposits (deposit), withdrawals (withdraw), and transfers (transfer) between accounts. Each account has a unique account number.

Implement a BankAccount class with the methods deposit, withdraw, and getBalance, supporting multithreaded interaction.

Implement a ConcurrentBank class to manage accounts and perform transfers between them. The class should provide a createAccount method for creating a new account and a transfer method for performing transfers between accounts.

Transfers between accounts must be atomic to avoid situations where one part of a transaction is completed successfully while the other is not.

Implement a getTotalBalance method that returns the total balance of all accounts in the bank.

public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Creating accounts
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Transfers between accounts
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Output total balance
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}
