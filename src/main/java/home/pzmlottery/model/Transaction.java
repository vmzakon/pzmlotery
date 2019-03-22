package home.pzmlottery.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDINBLOCKCHAIN")
    private String idInBlockchain;

    @Column(name = "TIMESTAMP")
    private Long timestamp;

    @Column(name = "SENDERRS")
    private String senderRS;

    @Column(name = "TYPE")
    private Type type ;

    @Column(name = "AMOUNTNQT")
    private Long amountNQT;

    @Column(name = "CONFIRMATION")
    private Long confirmations;

    @Column(name = "COMMENT")
    private String comment;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdInBlockchain() {
        return idInBlockchain;
    }

    public void setIdInBlockchain(String idInBlockchain) {
        this.idInBlockchain = idInBlockchain;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idInBlockchain, that.idInBlockchain) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(senderRS, that.senderRS) &&
                type == that.type &&
                Objects.equals(amountNQT, that.amountNQT) &&
                Objects.equals(confirmations, that.confirmations) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idInBlockchain, timestamp, senderRS, type, amountNQT, confirmations, comment);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idInBlockchain=" + idInBlockchain +
                ", timestamp=" + timestamp +
                ", senderRS='" + senderRS + '\'' +
                ", type=" + type +
                ", amountNQT=" + amountNQT +
                ", confirmations=" + confirmations +
                ", comment='" + comment + '\'' +
                '}';
    }

    public enum Type {
        IN,
        OUT,
        GENESIS
    }
}
