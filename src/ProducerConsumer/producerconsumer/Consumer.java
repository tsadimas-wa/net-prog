package producerconsumer;

class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Καταναλώνει 10 στοιχεία
            try {
                int value = buffer.take();
                // Εδώ θα γινόταν η "επεξεργασία" του value
                Thread.sleep(500); // Ο καταναλωτής είναι πιο αργός
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Επαναφέρουμε τη σημαία διακοπής
                return;                             // και σταματάμε το νήμα
            }
        }
    }
}
