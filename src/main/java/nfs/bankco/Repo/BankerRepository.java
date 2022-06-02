package nfs.bankco.Repo;

import nfs.bankco.Entity.Banker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    public List<Banker> getAll(){
        List<Banker> res = null;
        try {
            Query query = em.createQuery("Select id, firstname, lastname, phone, role, email FROM Banker");
            res = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
