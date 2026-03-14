import com.itk.BlockingQueue;
import com.itk.Consumer;
import com.itk.Producer;

public class Main {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        Thread producer1 = new Thread(new Producer(queue));
        Thread producer2 = new Thread(new Producer(queue));

        Thread consumer1 = new Thread(new Consumer(queue));
        Thread consumer2 = new Thread(new Consumer(queue));

        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
    }
}