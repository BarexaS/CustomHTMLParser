package pharser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class PageReaderImpl implements PageReader{
    @Override
    public String getPage(String link) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(link);
            URLConnection urlconn = url.openConnection();

            Map<String, List<String>> header = urlconn.getHeaderFields();
            List<String> content = header.get("Content-Type");
            String coding = content.get(0);
            String charset = coding.substring(coding.lastIndexOf('=')+1);

            if (!coding.substring(0,9).equalsIgnoreCase("text/html")){
                System.out.println("This URL - "+link+" does not contains HTML content");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), charset))) {
                for (String text = ""; (text = reader.readLine()) != null; ) {
                    sb.append(text);
                }
            }

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("This URL - "+link+" does not contains HTML content");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
