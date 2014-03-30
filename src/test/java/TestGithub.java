import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public final class TestGithub{
    @Test
    public void test()
    throws Exception{
        File DESKTOP = new File(new File(System.getProperty("user.home")), "Desktop");
        HttpURLConnection conn = (HttpURLConnection) new URL("https://api.github.com").openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        int response = conn.getResponseCode();
        System.out.println("Response Code: " + response);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DESKTOP, "Response.json")));
        String line;

        while((line = reader.readLine()) != null){
            writer.write(line);
            writer.newLine();
            writer.flush();
        }

        reader.close();
        writer.close();
    }
}