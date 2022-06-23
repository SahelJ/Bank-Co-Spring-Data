package nfs.bankco.Controller;

import nfs.bankco.Entity.BankBook;
import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Role;
import nfs.bankco.Repo.BankBookRepository;
import nfs.bankco.Repo.BankerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/superbanker") // avec authentification
public class SuperBankerController {
    private final BankerRepository bankerRepository;
    private final BankBookRepository bankBookRepository;

    public SuperBankerController(BankerRepository bankerRepository, BankBookRepository bankBookRepository) {
        this.bankerRepository = bankerRepository;
        this.bankBookRepository = bankBookRepository;
    }

    // Find list of all bankers
    @RequestMapping(value = "/list", produces = "application/json")
    public List<Banker> getAllBanker(){
        return bankerRepository.getAll();
    }
    // create new banker
    @PostMapping(
            value = "/banker/create",
            params = {"firstname", "lastname", "email", "phone"}
    )
    public void bankerCreate(
            // recuperation of param give by root
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
//            @RequestParam("role") List<Role> role,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ){
        // create a new instance of banker
        Banker banker = new Banker();
        // set value of fields in banker
        banker.setEmail(email);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_ADMIN);
        banker.setRole(roles);
        banker.setLastname(lastname);
        banker.setFirstname(firstname);
        banker.setPhone(phone);
        // call banker persistance by using bankerRepository
        bankerRepository.save(banker);
    }

    //update a banker
    @PatchMapping(
            value = "/banker/update",
            params = {"firstname", "lastname", "role", "email", "phone","id"}
    )
    public void bankerUpdate(
            // recuperation of param give by root
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("role") List<Role> role,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("id") int id
    ){
        // Get the target banker data
        Banker banker = bankerRepository.get(id);

        // set value of fields in banker
        banker.setEmail(email);
        banker.setRole(role);
        banker.setLastname(lastname);
        banker.setFirstname(firstname);
        banker.setPhone(phone);

        // call banker persistance by using bankerRepository
        bankerRepository.update(banker);
    }

    // delete banker
    @DeleteMapping(value = "/banker/delete/{id}")
    public void deleteBanker(@PathVariable(value="id") final int id){
        bankerRepository.delete(id);
    }

    // Find list of bankbook who have type livret A and who are not expired
    @RequestMapping(value = "/bankbookanotexpired" , produces = "application/json")
    public List<BankBook> getAllBankbook(){
        return bankBookRepository.getAllANotExpired();
    }

    // Find list of all bankbook
    @RequestMapping(value = "bankbooka" , produces = "application/json")
    public List<BankBook> getAllA(){
        return bankBookRepository.getAllA();
    }

    // create a bankbook
    @PostMapping(
            value = "/bankbook/create",
            params = {"rate", "fromDate", "toDate", "accountType"}
    )
    public void bankbookCreate(
            // recuperation of param give by root
            @RequestParam("rate") int rate,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("accountType") String accountType
    ){
        // create a new instance of bankbook
        BankBook bankbook = new BankBook();
        // set value of fields in bankbook
        bankbook.setAccountType(accountType);
        bankbook.setFromDate(fromDate);
        bankbook.setToDate(toDate);
        bankbook.setRate(rate);

        // call banker persistance by using bankbookRepository
        bankBookRepository.save(bankbook);
    }

    // update a bankbook
    @PatchMapping(
            value = "/bankbook/update",
            params = {"rate", "fromDate", "toDate", "accountType","id"}
    )
    public void bankbookUpdate(
            // recuperation of param give by root
            @RequestParam("rate") int rate,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("accountType") String accountType,
            @RequestParam("id") int id
    ){
        // create a new instance of bankbook
        BankBook bankbook = new BankBook();
        // set value of fields in bankbook
        bankbook.setAccountType(accountType);
        bankbook.setFromDate(fromDate);
        bankbook.setToDate(toDate);
        bankbook.setRate(rate);
        bankbook.setId(id);

        // call banker persistance by using bankbookrepository
        bankBookRepository.update(bankbook);
    }

    // delete bankbook
    @DeleteMapping(value = "/bankbook/delete/{id}")
    public void deleteBankbook(@PathVariable(value="id") final int id){
        bankBookRepository.delete(id);
    }
}