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
    @Column(name="from_date")
    private String fromDate;
    @Column(name="to_date")
    private String toDate;
    @Column(name="account_type")
    private String accountType;


    public int getId() {
        return id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
