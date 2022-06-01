package nfs.bankco.Controller;


import nfs.bankco.Entity.Banker;
import nfs.bankco.Repo.BankerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/auth/bankers") // avec authentification
public class BankController {

    @Autowired
    private BankerRepository bankerRepository;

    @RequestMapping(value = "/", produces = "application/json")
    public List<Banker> getAll(){
        return bankerRepository.findAll();
    }


}
