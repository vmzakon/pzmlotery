package home.pzmlottery.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    public enum Type {
        IN,
        OUT,
        GENESIS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDINBLOCKCHAIN")
    private String idinblockchain;

    @Column(name = "TIMESTAMP")
    private Long timestamp;

    @Column(name = "SENDER_RS")
    private String senderRS;

    @Column(name = "TYPE")
    private Type type ;

    @Column(name = "AMOUNT_NQT")
    private Long amountNQT;

    @Column(name = "CONFIRMATION")
    private Long confirmations;

    @Column(name = "COMMENT")
    private String comment;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "DRAW_ID")
    private Draw draw;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdinblockchain() {
        return idinblockchain;
    }

    public void setIdinblockchain(String idinblockchain) {
        this.idinblockchain = idinblockchain;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderRS() {
        return senderRS;
    }

    public void setSenderRS(String senderRS) {
        this.senderRS = senderRS;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getAmountNQT() {
        return amountNQT;
    }

    public void setAmountNQT(Long amountNQT) {
        this.amountNQT = amountNQT;
    }

    public Long getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Long confirmations) {
        this.confirmations = confirmations;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Draw getDraw() {
        return draw;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(idinblockchain, that.idinblockchain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idinblockchain, timestamp, senderRS, type, amountNQT, confirmations, comment, tickets, draw);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idinblockchain='" + idinblockchain + '\'' +
                ", timestamp=" + timestamp +
                ", senderRS='" + senderRS + '\'' +
                ", type=" + type +
                ", amountNQT=" + amountNQT +
                ", confirmations=" + confirmations +
                ", comment='" + comment + '\'' +
                ", draw=" + draw +
                '}';
    }
}
