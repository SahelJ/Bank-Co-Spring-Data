package nfs.bankco.Controller;

import nfs.bankco.Entity.Banker;
import nfs.bankco.Repo.BankerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth/superbanker") // avec authentification
public class SuperBankerController {
    @Autowired
    private BankerRepository bankerRepository;

    @RequestMapping(value = "/list", produces = "application/json")
    public List<Banker> getAll(){
        return bankerRepository.getAll();
    }

}
