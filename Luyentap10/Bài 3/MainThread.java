public class MainThread {
    public static void main(String[] args) {

        WorkerThread thread = new WorkerThread();
        thread.start();

        Thread runnableThread = new Thread(new WorkerRunnable());
        runnableThread.start();
    }
}