package nfs.bankco.Repo;

import nfs.bankco.Entity.BankBook;
import nfs.bankco.Entity.Banker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class BankBookRepository {
    @PersistenceContext
    private EntityManager em;
    public BankBook get(int id){
        BankBook res = null;
        try{
            res = em.find(BankBook.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public List<BankBook> getAllA(){
        List<BankBook> res = null;
        try{
            Query query = em.createQuery("SELECT id, rate, fromDate, toDate, accountType FROM BankBook");
            res = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }



    public List<BankBook> getAllANotExpired(){
        List<BankBook> res = null;
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.YEAR);
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<BankBook> query = cb.createQuery(BankBook.class);
            Root<BankBook> root = query.from(BankBook.class);
            query.select(root).where(cb.lessThan(root.get("toDate"), today));
            TypedQuery<BankBook> result = em.createQuery(query);
            res=result.getResultList();
        } catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public BankBook update(BankBook bankBook) {
        try {
            em.merge(bankBook);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankBook;
    }

    public void delete(int id) {
        try {
            Query query = em.createQuery(
                    "DELETE  FROM BankBook WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(BankBook bankBook) {
        try {
            em.persist(bankBook);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
