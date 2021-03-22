import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Callable;

public class HelloCountThread implements Callable<Result> {
    @Override
    public Result call() throws Exception {
        int printCount = 0;

        Random random = new Random();
        int target = random.nextInt(4) + 1;

        try {
            for (int i = 0; i < target; i++)
             {
                System.out.printf("Всем привет! Я %s. и сейчас на часах %s \n", Thread.currentThread().getName(), LocalDateTime.now());
                printCount++;
                Thread.sleep(1000);//Вводим некоторое различие
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.printf("Поток %s завершен\n", Thread.currentThread().getName());
        }

        return new Result(Thread.currentThread().getName(), printCount);
    }
}
