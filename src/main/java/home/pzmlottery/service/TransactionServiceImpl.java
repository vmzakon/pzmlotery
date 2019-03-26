package home.pzmlottery.service;

import home.pzmlottery.model.Draw;
import home.pzmlottery.model.Ticket;
import home.pzmlottery.model.Transaction;
import home.pzmlottery.repository.DrawRepository;
import home.pzmlottery.repository.TransactionRepository;
import home.pzmlottery.util.HttpClientRequest;
import home.pzmlottery.util.PrizmURIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DrawRepository drawRepository;

    private JsonArray getJsonArray(Integer howmuch) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("requestType", "getBlockchainTransactions"));
        nameValuePairs.add(new BasicNameValuePair("account", "PRIZM-F32X-V3JC-ARZK-BGMHE"));
        nameValuePairs.add(new BasicNameValuePair("lastIndex", howmuch.toString()));
        try {
            String response = HttpClientRequest.getPost(PrizmURIBuilder.getURL(nameValuePairs));
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

    @Override
    public List<Transaction> getNewTransactionList() {
        List<Transaction> transactionList;
        List<Transaction> newTransactionList = new ArrayList<>();
        Transaction last = transactionRepository.getLastTransaction();
        Draw draw = drawRepository.getDrawByStatus(Draw.Status.OPEN);
        Integer howmuch = 10;

        while (true) {
            JsonArray arrayTransactions = getJsonArray(howmuch);
            transactionList = new ArrayList<>();
            for (JsonElement jsonElement:  arrayTransactions) {
                Transaction tx = new Transaction();
                JsonObject object = jsonElement.getAsJsonObject();
                tx.setIdinblockchain(object.get("transaction").getAsString());
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
                tx.setDraw(draw);
                transactionList.add(tx);
            }
            if(!transactionList.contains(last)){
                if(transactionList.size() < howmuch+1) break;
                else howmuch*=2;
            }else break;
        }

        for (Transaction t: transactionList) {
            if (t.getIdinblockchain().equals(last.getIdinblockchain())){
                break;
            } else {
                newTransactionList.add(t);
            }
        }
        Collections.reverse(newTransactionList);
        return newTransactionList;
    }

    private String getCommentByIdInBlockchain(String id) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("requestType", "readMessage"));
        nameValuePairs.add(new BasicNameValuePair("secretPhrase", ""));
        nameValuePairs.add(new BasicNameValuePair("transaction", id));
        try {
            String response = HttpClientRequest.getPost(PrizmURIBuilder.getURL(nameValuePairs));
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
