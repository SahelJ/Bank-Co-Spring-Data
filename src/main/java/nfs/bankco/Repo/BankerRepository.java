package nfs.bankco.Repo;

import nfs.bankco.Entity.Banker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankerRepository extends JpaRepository<Banker, Integer>{
    Banker findByLastnameAndFirstname(String B_lastname, String B_firstname);
}
