package home.pzmlottery.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DRAWS")
public class Draw {

    public enum Status {
        OPEN,
        PAYWAIT,
        COMPLETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNTPRIZEPOOL")
    private Long amountrizepool;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "TIMESTAMPSTART")
    private Long timestampstart;

    @Column(name = "TIMESTAMPCLOSE")
    private Long timestampclose;

    @OneToMany(mappedBy = "draw", fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmountrizepool() {
        return amountrizepool;
    }

    public void setAmountrizepool(Long amountrizepool) {
        this.amountrizepool = amountrizepool;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTimestampstart() {
        return timestampstart;
    }

    public void setTimestampstart(Long timestampstart) {
        this.timestampstart = timestampstart;
    }

    public Long getTimestampclose() {
        return timestampclose;
    }

    public void setTimestampclose(Long timestampclose) {
        this.timestampclose = timestampclose;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Draw draw = (Draw) o;
        return Objects.equals(id, draw.id) &&
                Objects.equals(amountrizepool, draw.amountrizepool) &&
                status == draw.status &&
                Objects.equals(timestampstart, draw.timestampstart) &&
                Objects.equals(timestampclose, draw.timestampclose) &&
                Objects.equals(transactions, draw.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountrizepool, status, timestampstart, timestampclose, transactions);
    }

    @Override
    public String toString() {
        return "Draw{" +
                "id=" + id +
                ", amountrizepool=" + amountrizepool +
                ", status=" + status +
                ", timestampstart=" + timestampstart +
                ", timestampclose=" + timestampclose +
                '}';
    }
}
