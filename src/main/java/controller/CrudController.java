package controller;

import model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import repository.CustomerRepository;

import javax.annotation.Resource;

@Controller
public class CrudController {

    @Resource
    private CustomerRepository repository;

    @PostMapping("/add")
    public String saveCustomer(
            @RequestParam("first") String first,
            @RequestParam("last") String last,
            @RequestParam("phone") String phone,
            Model model
    ) {
        Customer customer = new Customer(first, last, phone);
        Customer dbCustomer = repository.save(customer);
        model.addAttribute("customer", dbCustomer);
        return "customer";
    }

    @GetMapping("/remove")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeCustomer(@RequestParam("id") Long id) {
        repository.deleteById(id);
    }

    @PostMapping("/change")
    public String updateCustomer(
            @RequestParam("id") Long id,
            @RequestParam("first") String first,
            @RequestParam("last") String last,
            @RequestParam("phone") String phone,
            Model model
    ) {

        Customer customer = repository.findById(id).orElseThrow(RuntimeException::new);
        if(!first.isEmpty()) {
            customer.setFirstName(first);
        }
        if(!last.isEmpty()) {
            customer.setLastName(last);
        }
        if(!phone.isEmpty()) {
            customer.setPhoneNumber(phone);
        }

        Customer dbCustomer = repository.save(customer);
        model.addAttribute("customer", dbCustomer);

        return "updateCustomer";
    }

    @GetMapping("/list")
    public String getCustomers(Model model) {
        model.addAttribute("list", repository.findAll());
        return "blacklist";
    }
}
