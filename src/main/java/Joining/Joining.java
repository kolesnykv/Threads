package Joining;

import lombok.SneakyThrows;

class Sleeper extends Thread {
    private int duration;
    public Sleeper (String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }
    @SneakyThrows
    public void run() {
        sleep(duration);
        System.out.println(getName() + " has been activated");
    }
}
class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }
    @SneakyThrows
    public void run() {
        sleeper.join();
        System.out.println(getName() + " joining is completed");
    }
}
public class Joining {
    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Sleeper grumpy = new Sleeper("Grumpy", 1500);
        Joiner dopey = new Joiner("Dopey", sleepy);
        Joiner doc = new Joiner("Doс", grumpy);
        grumpy.interrupt();
    }
}
