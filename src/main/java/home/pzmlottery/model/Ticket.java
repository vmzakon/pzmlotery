package home.pzmlottery.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TICKETS")
public class Ticket {

    public enum Status {
        INDRAW,
        PAYWAIT,
        COMPLETE

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRANSACTION_ID" )
    private Transaction transaction;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "POSITION")
    private Long position;

    @Column(name = "AMOUNTPRIZE")
    private Long amountPrize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getAmountPrize() {
        return amountPrize;
    }

    public void setAmountPrize(Long amountPrize) {
        this.amountPrize = amountPrize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(transaction, ticket.transaction) &&
                status == ticket.status &&
                Objects.equals(position, ticket.position) &&
                Objects.equals(amountPrize, ticket.amountPrize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transaction, status, position, amountPrize);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", status=" + status +
                ", position=" + position +
                ", amountPrize=" + amountPrize +
                '}';
    }
}
