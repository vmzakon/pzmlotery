package home.pzmlottery.repository;

import home.pzmlottery.model.Transaction;


public interface TransactionRepository {

    void saveTransaction(Transaction transaction);
    Transaction getLastTransaction();
    Transaction getTransactionByIdInBlockchain(String idInBlockchain);


}
