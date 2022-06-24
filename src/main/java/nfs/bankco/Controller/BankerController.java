package nfs.bankco.Controller;


import nfs.bankco.Config.JWTTokenProvider;
import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Customer;
import nfs.bankco.Repo.BankerRepository;
import nfs.bankco.Repo.CustomerRepository;
import nfs.bankco.Services.JWTUserService;
import nfs.bankco.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth/banker")
public class BankerController {

    @Autowired
    private BankerRepository bankerRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private JWTUserService userService;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/test", produces = "application/json")
    public String Test(){
        return "Hello";
    }


    @RequestMapping(value = "/customers", produces = "application/json")
    public List<Customer> getCustomersWallet(HttpServletRequest req){
        String id = jwtTokenProvider.getId(req);
        System.out.println("ID ? = " + id);
//        userService.loadUserByUsername()
        return bankerRepository.getCustomerWallet(Integer.parseInt(id));
    }

    @RequestMapping(value = "/update", produces = "application/json")
    public Customer update(@PathVariable(value="id") final String id, HttpServletRequest request){
        Customer customer = new Customer();
        return customerRepository.update(customer);
    }

}
