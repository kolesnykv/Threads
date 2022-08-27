package PingPong;

import lombok.SneakyThrows;

public class PingPongBasic {
    public static void main(String[] args) {
        Object monitor = new Object();
        Thread ping = new Thread(new PingPongThread(monitor, "Ping"));
        Thread pong = new Thread(new PingPongThread(monitor, "Pong"));
        ping.start();
        pong.start();
    }
}

class PingPongThread implements Runnable {


    private final Object monitor;
    private final String threadName;
    public PingPongThread(Object monitor, String threadName) {
        this.monitor = monitor;
        this.threadName = threadName;
    }

    @Override
    @SneakyThrows
    public void run() {
        synchronized (monitor) {
            for(int i=0;i<10;i++) {
                System.out.print(threadName+"!"+" ");
                Thread.sleep(500);
                monitor.notify();
                monitor.wait(1000);
            }
            System.out.println("game over!");
        }
    }
}

