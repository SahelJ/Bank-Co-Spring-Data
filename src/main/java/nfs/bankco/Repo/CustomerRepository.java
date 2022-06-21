package nfs.bankco.Repo;

import nfs.bankco.Entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;
    public Customer get(int id){
        Customer res = null;
        try{
            res = em.find(Customer.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public Object getCustomerDetails(int id){
        Object res = null;
        try {
            Query query = em.createQuery("Select id, firstname, lastname, phone, email, birthdate, address, city, country, customerNumber, isNewCustomer, title, postal FROM Customer WHERE id = :id");
            query.setParameter("id", id);
            res = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public List<Customer> getAll(){
        List<Customer> res = null;
        try {
            Query query = em.createQuery("Select id, firstname, lastname, phone, email FROM Customer");
            res = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public void save(Customer customer) {
        try {
            em.persist(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Customer update(Customer customer) {
        try {
            em.merge(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void delete(int id) {
        try {
            Query query = em.createQuery(
                    "DELETE  FROM Customer WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
