package nfs.bankco.Entity;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "bankbooks")
public class BankBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bankbook")
    private int id;
    private int rate;
    private String fromDate;
    private String toDate;

    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }
}
