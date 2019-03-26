package home.pzmlottery.service;

import home.pzmlottery.model.Ticket;
import home.pzmlottery.model.Transaction;
import home.pzmlottery.repository.TicketRepository;
import home.pzmlottery.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void saveNewTickets() {
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionService.getNewTransactionList();
        if(!transactions.isEmpty()) {
            for (Transaction tx: transactions) {
                transactionRepository.saveTransaction(tx);
                if((tx.getType().equals(Transaction.Type.IN) && (tx.getAmountNQT() > 10000))) {
                    Transaction txwithid = transactionRepository.getTransactionByIdInBlockchain(tx.getIdinblockchain());
                    Long amount = tx.getAmountNQT();
                    Long howTicket = (amount - amount%10000)/10000;
                    Ticket ticket = new Ticket();
                    ticket.setStatus(Ticket.Status.INDRAW);
                    ticket.setTransaction(txwithid);
                    for (int i = 0; i < howTicket ; i++) {
                        ticketRepository.saveNewTicket(ticket);
                    }
                }
            }
        }
    }
}

