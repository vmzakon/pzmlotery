package home.pzmlottery.repository;

import home.pzmlottery.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class TransactionRepositoryImp implements TransactionRepository{

    @Override
    public void insertTransaction() {

    }

    @Override
    public List<Transaction> getAllTransaction() {
        return null;
    }

    @Override
    public Transaction getLastTransaction() {
        return null;
    }
}
