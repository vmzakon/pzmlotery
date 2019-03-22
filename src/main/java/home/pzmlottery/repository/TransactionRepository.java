package home.pzmlottery.repository;

import home.pzmlottery.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void insertTransaction();
    List<Transaction> getAllTransaction();
    Transaction getLastTransaction();

}
