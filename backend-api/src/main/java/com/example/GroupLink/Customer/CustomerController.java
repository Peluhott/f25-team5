package com.example.GroupLink.Customer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/customer/login")
    public Object login(){
        return "login/login-screen";
    }

    @GetMapping("/customer/signup-options")
    public Object signupOptions(){
        return "signup/signup-option";
    }

    @GetMapping("/customer/signup")
    public Object signup(Model model){
        model.addAttribute("customer", new Customer());
        return "signup/customer-signup";
    }

    @PostMapping("/customer/login")
    public Object loginPost(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, Model model){
        if(customerService.authenticate(username, password)){
            return "redirect:/customer/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login/login-screen";
        }
    }

    @PostMapping("/customer/signup")
    public Object signupPost(@RequestParam(value = "username") String username,
    @RequestParam(value = "email") String email,
    @RequestParam(value = "password") String password,
    @RequestParam(value = "re-password") String rePassword,
    @RequestParam(value = "profilePicture", required = false) String profilePicture,
    Model model){
        if(customerService.isEmailTaken(email)){
            model.addAttribute("error", "Email is already registered");
            return "signup/customer-signup";
        }
        if(customerService.isUsernameTaken(username)){
            model.addAttribute("error", "Username is already taken");
            return "signup/customer-signup";
        }
        customerService.createCustomer(new Customer(email, username, password, profilePicture));
        return "redirect:/customer/login";
    }

    @GetMapping("/customer/home")
    public Object home(Model model){
        model.addAttribute("groupList", groupService.getAllGroups());
        model.addAttribute("groupLocations", groupService.getDistinctGroupLocations());
        model.addAttribute("groupTypes", groupService.getDistinctGroupTypes());
        model.addAttribute("title", "GroupLink");
        return "customer/customer-home";
    }

    @GetMapping("/customer/home/search")
    public Object searchGroups(@RequestParam(value = "query", required = false) String query, Model model){
        model.addAttribute("groupLocations", groupService.getDistinctGroupLocations());
        model.addAttribute("groupTypes", groupService.getDistinctGroupTypes());
        model.addAttribute("groupList", groupService.searchGroupsByName(query));
        model.addAttribute("title", "GroupLink");
        return "customer/customer-home";
    }

    @GetMapping("/customer/home/filter")
    public Object Groups(@RequestParam(value = "type", required = false) String type,
    @RequestParam(value = "location", required = false) String location, 
    @RequestParam(value = "rating", required = false) String rating ,Model model){
        model.addAttribute("groupLocations", groupService.getDistinctGroupLocations());
        model.addAttribute("groupTypes", groupService.getDistinctGroupTypes());
        model.addAttribute("groupList", groupService.filterGroups(type, location, rating));
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
