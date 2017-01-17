package thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResultFile {
    public static void writeResult(String filePath, String link, String md5Hash)
    {
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(link);
            fileWriter.write(" - ");
            fileWriter.write(md5Hash);
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
