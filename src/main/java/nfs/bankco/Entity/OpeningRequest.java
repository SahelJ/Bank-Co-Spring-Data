package nfs.bankco.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new_customer_requests")
public class OpeningRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_new_customer_requests")
    private long id;
    private String title;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private Date birthdate;
    private String address;
    private String city;
    private String postal;
    private String country;
    private String idCard;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Date getBirthdate() { return birthdate; }

    public void setBirthdate(Date birthdate) { this.birthdate = birthdate; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getPostal() { return postal; }

    public void setPostal(String postal) { this.postal = postal;}

    public String getCountry() { return country;}

    public void setCountry(String country) { this.country = country;}

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
