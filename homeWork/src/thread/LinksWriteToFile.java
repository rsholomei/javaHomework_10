package thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LinksWriteToFile {
    public static void write(String pathName, String links)
    {
        File file = new File(pathName);
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(links);
            writer.flush();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
