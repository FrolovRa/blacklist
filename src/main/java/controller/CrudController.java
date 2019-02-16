package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CrudController {

    @GetMapping("/add")
    public String saveCustomer() {
        return "Blacklist";
    }

    @GetMapping("delete")
    public String removeCustomer() {
        return "Blacklist";
    }

    @PostMapping("/change")
    public String updateCustomer() {
        return "Blacklist";
    }

    @GetMapping("/list")
    public String getCustomers() {
        return "Blacklist";
    }
}
