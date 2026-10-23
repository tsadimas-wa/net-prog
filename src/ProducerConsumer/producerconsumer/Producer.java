package producerconsumer;

class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Παράγει 10 στοιχεία
            try {
                buffer.put(i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Επαναφέρουμε τη σημαία διακοπής
                return;                             // και σταματάμε το νήμα
            }
        }
    }
}
