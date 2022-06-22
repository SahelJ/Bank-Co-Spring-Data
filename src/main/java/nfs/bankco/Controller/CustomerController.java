package nfs.bankco.Controller;


import nfs.bankco.Entity.Customer;
import nfs.bankco.Repo.CustomerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // instance of request body to create customer
    public static class CreateForm{
        private String firstname;
        private String lastname;
        private String title;
        private String phone;
        private String email;
        private String birthdate;
        private String address;
        private String postal;
        private String city;
        private String country;
        private String customerNumber;
        private String password;

    }

    @RequestMapping(value = "/{id}", produces = "application/json")
    public Object getOneCustomerById(@PathVariable(value="id") final String id){
        return customerRepository.getCustomerDetails(Integer.parseInt(id));
    }

    @RequestMapping(value = "/update", produces = "application/json")
    public Customer update(@PathVariable(value="id") final String id){
        Customer customer = new Customer();
        return customerRepository.update(customer);
    }

    @PostMapping(value = "/create")
    public void customerCreate(@RequestBody CreateForm createform){
        // create a new instance of Customer
        Customer customer = new Customer();
        // set value of fields in Customer
        customer.setFirstname(createform.firstname);
        customer.setLastname(createform.lastname);
        customer.setTitle(createform.title);
        customer.setPhone(createform.phone);
        customer.setBirthdate(createform.birthdate);
        customer.setAddress(createform.address);
        customer.setCity(createform.city);
        customer.setCountry(createform.country);
        customer.setCustomerNumber(createform.customerNumber);
        customer.setPassword(createform.password);
        customer.setNewCustomer(true);
        // persist customer data
        customerRepository.save(customer);
    }

    // delete customer
    @DeleteMapping(value = "/bankbook/delete/{id}")
    public void deleteCustomer(@PathVariable(value="id") final int id){
        customerRepository.delete(id);
    }
}
