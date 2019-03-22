package home.pzmlottery.service;

import home.pzmlottery.model.Transaction;
import home.pzmlottery.util.HttpClientRequest;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl {
    private static final String REQUEST_URI ="http://localhost:9976/prizm";

    private JsonArray getJsonArray() throws URISyntaxException {

        URIBuilder uriBuilder = new URIBuilder(REQUEST_URI);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("requestType", "getBlockchainTransactions"));
        nameValuePairs.add(new BasicNameValuePair("account", "PRIZM-F32X-V3JC-ARZK-BGMHE"));
        nameValuePairs.add(new BasicNameValuePair("lastIndex", "10"));
        uriBuilder.addParameters(nameValuePairs);
        URI uri = uriBuilder.build();

        try {
            String response = HttpClientRequest.getPost(uri.toString());
            JsonArray arrayTransactions = new JsonParser()
                    .parse(response)
                    .getAsJsonObject()
                    .getAsJsonArray("transactions");
            return arrayTransactions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Transaction> getTransactionList() throws URISyntaxException {
        JsonArray arrayTransactions = getJsonArray();
        List<Transaction> transactionList = new ArrayList<>();
        for (JsonElement jsonElement:  arrayTransactions) {
            Transaction tx = new Transaction();
            JsonObject object = jsonElement.getAsJsonObject();
            tx.setIdInBlockchain(object.get("transaction").getAsString());
            tx.setTimestamp((object.get("timestamp").getAsLong())*1000 + 1532715479000L);
            tx.setSenderRS(object.get("senderRS").getAsString());
            if(object.get("senderRS").getAsString().equals("PRIZM-F32X-V3JC-ARZK-BGMHE")) {
                tx.setType(Transaction.Type.OUT);
            }else if(object.get("senderRS").getAsString().equals("PRIZM-TE8N-B3VM-JJQH-5NYJB")){
                tx.setType(Transaction.Type.GENESIS);
            }else tx.setType(Transaction.Type.IN);
            tx.setAmountNQT(object.get("amountNQT").getAsLong());
            tx.setConfirmations(object.get("confirmations").getAsLong());
            tx.setComment(getCommentByIdInBlockchain(object.get("transaction").getAsString()));
            transactionList.add(tx);
        }
        return transactionList;
    }

    private String getCommentByIdInBlockchain(String id) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(REQUEST_URI);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("requestType", "readMessage"));
        nameValuePairs.add(new BasicNameValuePair("secretPhrase", "yet glad puff somehow too shiver constantly flicker knowledge echo time hurry stole large bone nod"));
        nameValuePairs.add(new BasicNameValuePair("transaction", id));
        uriBuilder.addParameters(nameValuePairs);
        URI uri = uriBuilder.build();

        try {
            String response = HttpClientRequest.getPost(uri.toString());
            String comment = new JsonParser()
                    .parse(response)
                    .getAsJsonObject()
                    .get("decryptedMessage").getAsString();
            return comment;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException n) {
            return null;
        }
        return null;
    }

}
