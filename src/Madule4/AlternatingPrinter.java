package Madule4;

public class AlternatingPrinter {
    private static final Object lock = new Object();
    private static volatile boolean printOne = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!printOne) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("1");
                    printOne = false;
                    lock.notifyAll();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (printOne) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("2");
                    printOne = true;
                    lock.notifyAll();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
