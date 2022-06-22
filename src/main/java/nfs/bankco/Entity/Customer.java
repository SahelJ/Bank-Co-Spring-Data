package nfs.bankco.Entity;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")

    private int id;
    private String firstname;
    private String lastname;
    private String title;
    private String phone;
    private String email;
    private String birthdate;
    private String address;
    private String postal;
    private String city;
    private String country;
    private String customerNumber;
    private String password;
    private Boolean isNewCustomer;

    private int banker_id;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Account> accounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    //    public Collection<Account> getAccounts() {
//        return accounts;
//    }
    public Account getAccountById(int id) {
        Account account = null;
        for (Account acc:
                accounts) {
            if (acc.getId() == id) {
                account = acc;
            }
        }

        return account;
    }

    public Boolean getNewCustomer() {
        return isNewCustomer;
    }

    public void setNewCustomer(Boolean newCustomer) {
        isNewCustomer = newCustomer;
    }


}
