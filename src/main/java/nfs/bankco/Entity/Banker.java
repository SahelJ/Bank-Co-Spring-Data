package nfs.bankco.Entity;

import java.util.Objects;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="bankers")
public class Banker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_banker")
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String role;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "banker_id")
    private List<Customer> customers;

    public Banker() {
        this.firstname = "";
        this.lastname = "";
    }

    public Banker(int id, String firstname, String lastname) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Banker [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstname, id, lastname);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Banker other = (Banker) obj;
        return Objects.equals(firstname, other.firstname) && id == other.id && Objects.equals(lastname, other.lastname);
    }

}
