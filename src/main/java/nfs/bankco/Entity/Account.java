package nfs.bankco.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer_account")
    private int id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_type")
    private String accountType;
    private int balance;
    @Column(name = "authorized_debt")
    private int authorizedDebt;

    // CREDITS
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "from_account")
    private List<Transaction> credits;

    // DEBITS
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "to_account")
    private List<Transaction> debts;

    public int getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getBalance() {
        return balance;
    }

    public int getAuthorizedDebt() {
        return authorizedDebt;
    }

    public List<Transaction> getCredits() {
        return credits;
    }

    public void setCredits(List<Transaction> credits) {
        this.credits = credits;
    }

    public List<Transaction> getDebts() {
        return debts;
    }

    public void setDebts(List<Transaction> debts) {
        this.debts = debts;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
