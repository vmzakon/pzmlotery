package home.pzmlotery.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BUY_DATE")
    private Date buydate;

    public Ticket(Long id) {
    }
}
