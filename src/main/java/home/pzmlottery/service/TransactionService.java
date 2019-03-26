package home.pzmlottery.service;
import home.pzmlottery.model.Transaction;
import java.util.List;

public interface TransactionService  {

    List<Transaction> getNewTransactionList();
}
