package changgeIP;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Test {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://qsrdk.daili666api.com/ip/?tid=556604539534146&num=30¡¢");
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) >= 0) {
            System.out.println(new String(bytes));
        }
    }
}
