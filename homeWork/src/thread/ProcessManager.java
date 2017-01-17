package thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
    public static String getContentByLink(String link) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(link);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null)
                {
                    builder.append(line);
                    builder.append("\n");
                }
            } catch (MalformedURLException e)
            {
                System.out.println(e.getMessage());
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return builder.toString();
    }
    public static String getMD5hash(String content)
    {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(content.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        String md5Hash = bigInteger.toString(16);
        while (md5Hash.length() < 32)
        {
            md5Hash = "0" + md5Hash;
        }
        return md5Hash;
    }
    public static List<String> readLinksFromFile(String path)
    {
        List<String> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                list.add(line);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static synchronized void run(String link, String path)
    {
        String content = ProcessManager.getContentByLink(link);
        String md5Hash = ProcessManager.getMD5hash(content);
        ResultFile.writeResult(path, link, md5Hash);

    }
}
