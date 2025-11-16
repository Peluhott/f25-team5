package com.example.GroupLink.Customer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.GroupLink.Group.GroupService;
import com.example.GroupLink.Provider.ProviderService;

@Controller
public class CustomerController {
    
    private CustomerService customerService;
    private GroupService groupService;
    private ProviderService providerService;

    public CustomerController(CustomerService customerService, GroupService groupService, ProviderService providerService) {
        this.customerService = customerService;
        this.groupService = groupService;
        this.providerService = providerService;
    }

    @GetMapping("/customer/home")
    public Object getAllGroups(Model model){
        model.addAttribute("groupList", groupService.getAllGroups());
        model.addAttribute("title", "GroupLink");
        return "customer/customer-home";
    }

    @GetMapping("/group/details/{id}")
    public Object getGroupDetails(@PathVariable Long id, Model model){
        long providerId = groupService.getGroupById(id).getProvider().getId();
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("title", "Group Details");
        model.addAttribute("memberList", groupService.getGroupMembers(id));
        model.addAttribute("reviewList", providerService.getAllReviewsForProvider(providerId));
        return "customer/group-details";
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Validated @RequestBody Customer customerDetails) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
