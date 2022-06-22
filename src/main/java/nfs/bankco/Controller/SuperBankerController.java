package nfs.bankco.Controller;

import nfs.bankco.Entity.BankBook;
import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Role;
import nfs.bankco.Repo.BankBookRepository;
import nfs.bankco.Repo.BankerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth/superbanker") // avec authentification
public class SuperBankerController {
    @Autowired
    private BankerRepository bankerRepository;
    @Autowired
    private BankBookRepository bankBookRepository;

    // Find list of all bankers
    @RequestMapping(value = "/list", produces = "application/json")
    public List<Banker> getAllBanker(){
        return bankerRepository.getAll();
    }
    // create new banker
    @RequestMapping(
            value = "/banker/create",
            params = {"firstname", "lastname", "role", "email", "phone"},
            method = RequestMethod.POST
    )
    public void bankerCreate(
            // recuperation of param give by root
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("role") List<Role> role,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone
    ){
        // create a new instance of banker
        Banker banker = new Banker();
        // set value of fields in banker
        banker.setEmail(email);
        banker.setRole(role);
        banker.setLastname(lastname);
        banker.setFirstname(firstname);
        banker.setPhone(phone);
        // call banker persistance by using bankerRepository
        bankerRepository.save(banker);
    }

    //update a banker
    @RequestMapping(
            value = "/banker/update",
            params = {"firstname", "lastname", "role", "email", "phone","id"},
            method = RequestMethod.PATCH
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
    @RequestMapping(
            value = "/banker/delete",
            params = {"id"},
            method = RequestMethod.DELETE
    )
    public void deleteBanker(@RequestParam("id") int id){
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
    @RequestMapping(
            value = "/bankbook/create",
            params = {"rate", "fromDate", "toDate", "accountType"},
            method = RequestMethod.POST
    )
    public void bankbookCreate(
            // recuperation of param give by root
            @RequestParam("rate") int rate,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            @RequestParam("accountType") String accountType
    ){
        // create a new instance of banker
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
    @RequestMapping(
            value = "/bankbook/update",
            params = {"rate", "fromDate", "toDate", "accountType","id"},
            method = RequestMethod.PATCH
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
    @RequestMapping(
            value = "/bankbook/delete",
            params = {"id"},
            method = RequestMethod.DELETE
    )
    public void deleteBankbook(@RequestParam("id") int id){
        bankBookRepository.delete(id);
    }
}