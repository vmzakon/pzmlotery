package home.pzmlottery.repository;

import home.pzmlottery.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;


@Repository
@Transactional
public class TransactionRepositoryImpl implements TransactionRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTransaction(Transaction transaction) {
        entityManager.merge(transaction);
    }

    @Override
    public Transaction getLastTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        Transaction trx;
        try {
            trx = entityManager.
                    createQuery("select t from Transaction t order by t.id desc ", Transaction.class).setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new Transaction();
        }
        return trx;
    }

    @Override
    public Transaction getTransactionByIdInBlockchain(String idInBlockchain) {
        Transaction transaction;
        try {
            transaction = entityManager.
                    createQuery("select t from Transaction t WHERE t.idinblockchain = :idinblockchain", Transaction.class)
                    .setParameter("idinblockchain", idInBlockchain)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return transaction;
    }

}
