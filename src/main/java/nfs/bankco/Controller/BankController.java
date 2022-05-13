package nfs.bankco.Controller;


import nfs.bankco.Repo.BankerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    @GetMapping("/")
    public String index(){
        return "Hello World!";
    }
    @Autowired
    private BankerRepository repo;

}
