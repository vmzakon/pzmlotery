package home.pzmlottery.repository;

import home.pzmlottery.model.Ticket;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class TicketRepositoryImpl implements TicketRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveNewTicket(Ticket ticket) {
        entityManager.merge(ticket);
    }
}
