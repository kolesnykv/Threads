package PingPong;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PingPongAdvanced {
    public static void main(String[] args) {
        BlockingQueue<String> fromPingToPong = new LinkedBlockingQueue<>();
        BlockingQueue<String> fromPongToPing = new LinkedBlockingQueue<>();
        Thread ping = new EventProcessor("Ping", fromPingToPong, fromPongToPing);
        Thread pong = new EventProcessor("Pong", fromPongToPing, fromPingToPong);
        ping.start();
        pong.start();
        fromPingToPong.add("Ping");
    }

    @RequiredArgsConstructor
    static class EventProcessor extends Thread {
        private final String event;
        private final BlockingQueue<String> sendTo;
        private final BlockingQueue<String> readFrom;

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                String value = readFrom.take();
                Thread.sleep(1000);
                System.out.println(value);
                sendTo.add(event);
            }
        }
    }
}
