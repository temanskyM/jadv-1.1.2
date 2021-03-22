import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<HelloCountThread> threadList = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threadList.add(new HelloCountThread());
        }

        //Ожидаем выполнения всех задач
        System.out.println("Запускаем на выполнение. Ожидаем все задачи");
        List<Future<Result>> resultList = new ArrayList<>();
        try {
            resultList = executor.invokeAll(threadList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<Result> future : resultList) {
            try {
                Result result = future.get();
                System.out.println("Счетчик у потока " + result.getName() + " равен " + result.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        //Ожидаем выполнения одной задач
        System.out.println("Запускаем на выполнение. Ожидаем одну задачу");
        Result result = null;
        try {
            result = executor.invokeAny(threadList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Счетчик у потока " + result.getName() + " равен " + result.getCount());

    }
}
