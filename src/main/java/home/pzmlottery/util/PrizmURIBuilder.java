package home.pzmlottery.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class PrizmURIBuilder {
    private static final String REQUEST_URI ="http://localhost:9976/prizm";

    public static String getURL (List<NameValuePair> params) {
        URI uri = null;
        String string = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(REQUEST_URI);
            uriBuilder.setParameters(params);
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            string = "";
        }


        return uri.toString();
    }
}
