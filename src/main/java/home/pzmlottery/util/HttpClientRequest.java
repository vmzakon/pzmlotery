package home.pzmlottery.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

public class HttpClientRequest {


    private static final org.apache.http.client.HttpClient client = HttpClientBuilder.create().build();

    public static String getGet(String URI) throws IOException {
        HttpGet get = new HttpGet(URI);
        HttpResponse response = client.execute(get);
        String result = new BasicResponseHandler().handleResponse(response);
        return result;
    }

    public static String getPost(String URI) throws IOException {
        HttpPost post = new HttpPost(URI);

        HttpResponse response = client.execute(post);
        String result = new BasicResponseHandler().handleResponse(response);
        return result;
    }
}
