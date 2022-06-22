package nfs.bankco.Controller;


import nfs.bankco.Entity.Banker;
import nfs.bankco.Entity.Customer;
import nfs.bankco.Repo.BankerRepository;
import nfs.bankco.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banker")
public class BankerController {

    @Autowired
    private BankerRepository bankerRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @RequestMapping(value = "/test", produces = "application/json")
    public String Test(){
        return "Hello";
    }

    @RequestMapping(value = "/customers", produces = "application/json")
    public List<Customer> getCustomersWallet(){
        return bankerRepository.getCustomerWallet(2);
    }

    @RequestMapping(value = "/update", produces = "application/json")
    public Customer update(@PathVariable(value="id") final String id){
        Customer customer = new Customer();
        return customerRepository.update(customer);
    }

}
