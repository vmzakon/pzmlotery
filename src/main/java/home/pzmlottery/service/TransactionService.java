package home.pzmlottery.service;

import home.pzmlottery.model.Transaction;

import java.util.List;

public interface TransactionService  {

    void insertTransaction();
    List<Transaction> getAllTransaction();
    Transaction getLastTransaction();

}
