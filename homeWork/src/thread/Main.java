package thread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String FILE_PATH = "/home/ruslan/file.txt";
    private static final String RESULT_FILE_PATH = "/home/ruslan/result.txt";

    public static void main(String[] args) {
        LinksWriteToFile.write(FILE_PATH,
                "http://vk.com/feed\n" +
                        "http://www.google.com.ua/\n" +
                        "http://habrahabr.ru/\n" +
                        "http://devcolibri.com/\n" +
                        "http://javadevblog.com/\n");
        List<String> links = ProcessManager.readLinksFromFile(FILE_PATH);
        ExecutorService taskExecutor = Executors.newFixedThreadPool(7);
        for (String link : links)
        {
            taskExecutor.execute(() -> ProcessManager.run(link, RESULT_FILE_PATH));
        }
        taskExecutor.shutdown();
    }
}
