package nfs.bankco.Controller;

import nfs.bankco.Entity.Customer;
import nfs.bankco.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/{id}", produces = "application/json")
    public Object getOneCustomerById(@PathVariable(value="id") final String id){
        return customerRepository.getCustomerDetails(Integer.parseInt(id));
    }

    @RequestMapping(value = "/update", produces = "application/json")
    public Customer update(@PathVariable(value="id") final String id){
        Customer customer = new Customer();
        return customerRepository.update(customer);
    }
}
