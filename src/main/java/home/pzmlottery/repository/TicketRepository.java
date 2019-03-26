package home.pzmlottery.repository;

import home.pzmlottery.model.Ticket;

public interface TicketRepository {
    void saveNewTicket(Ticket ticket);
}
