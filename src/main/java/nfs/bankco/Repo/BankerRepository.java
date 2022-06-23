package nfs.bankco.Repo;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class BankerRepository {
    @PersistenceContext
    private EntityManager em;
    public Banker get(int id){
        Banker res = null;
        try{
            res = em.find(Banker.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public Banker findUserWithEmail(String email){
        Banker res = null;
        try {
            Query query = em.createQuery("Select b FROM Banker b WHERE b.email = :email");
            query.setParameter("email", email);
            res = (Banker) query.getSingleResult();
        } catch (EntityNotFoundException e) {
//            e.printStackTrace();

        }
        return res;
    }

    public Banker findUserWithMail(String email){
        Banker res = null;
        try {
            Query query = em.createQuery("Select b FROM Banker b WHERE b.email = :email");
            query.setParameter("email", email);
            res = (Banker) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public List<Banker> getAll(){
        List<Banker> res = null;
        try {
            Query query = em.createQuery("SELECT b.firstname, b.lastname, b.email, b.phone, b.role FROM Banker b");
            res = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public void save(Banker banker) {
        try {
            em.persist(banker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Banker update(Banker banker) {
        try {
            em.merge(banker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return banker;
    }

    public void delete(int id) {
        try {
            Query query = em.createQuery(
                    "DELETE  FROM Banker WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomerWallet(int id) {
        List<Customer> res = null;
        try {
            Query query = em.createQuery("SELECT c.id, c.firstname, c.lastname FROM Customer c WHERE c.banker_id = :id ");
            query.setParameter("id", id);
            res = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
