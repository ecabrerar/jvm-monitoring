public class DeadlockDemo {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    void main() throws Exception {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                IO.println("T1: adquirí lockA");
                sleep(100);                       // da tiempo a t2 a tomar lockB
                IO.println("T1: quiero lockB...");
                synchronized (lockB) {             // 💀 espera eternamente
                    IO.println("T1: adquirí lockB");
                }
            }
        }, "hilo-1");

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                IO.println("T2: adquirí lockB");
                sleep(100);                       // da tiempo a t1 a tomar lockA
                IO.println("T2: quiero lockA...\n");
                synchronized (lockA) {             // espera eternamente
                    IO.println("T2: adquirí lockA\n");
                }
            }
        }, "hilo-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        IO.println("Este mensaje nunca se imprime: la app quedó bloqueada");
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { }
    }
}