package Callable;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.*;

@AllArgsConstructor
class TaskWithResult implements Callable<String> {
    private int id;
    @Override
    public String call() throws Exception {
        return "result TaskWithResult " +id;
    }
}
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i=1; i<10; i++) {
            results.add(es.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs: results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            finally {
                es.shutdown();
            }
        }
    }
}
