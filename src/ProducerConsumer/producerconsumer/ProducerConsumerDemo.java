package producerconsumer;

/**
 *
 * @author rg
 */
public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {

        Buffer buffer = new Buffer(5); // Κοινόχρηστος πόρος με χωρητικότητα 5

        // Δίνουμε ονόματα στα νήματα ώστε να φαίνεται στην έξοδο ποιο κάνει τι
        Thread producerThread = new Thread(new Producer(buffer), "Παραγωγός");
        Thread consumerThread = new Thread(new Consumer(buffer), "Καταναλωτής");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("\nΌλες οι εργασίες ολοκληρώθηκαν.");
    }

}
