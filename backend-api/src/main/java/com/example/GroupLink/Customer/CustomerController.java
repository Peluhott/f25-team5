package com.example.GroupLink.Customer;

import java.lang.ProcessBuilder.Redirect;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            return "redirect:/customer/home/" + customerService.getCustomerByUsername(username).getId();
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
    @RequestParam(value = "profilePicturePath", required = true) MultipartFile profilePicturePath,
    Model model){
        if(customerService.isEmailTaken(email)){
            model.addAttribute("error", "Email is already registered");
            return "signup/customer-signup";
        }
        if(customerService.isUsernameTaken(username)){
            model.addAttribute("error", "Username is already taken");
            return "signup/customer-signup";
        }
        if(!password.equals(rePassword)){
            model.addAttribute("error", "Passwords do not match");
            return "signup/customer-signup";
        }
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password);
        customerService.createCustomer(customer, profilePicturePath);
        return "redirect:/customer/login";
    }

    @GetMapping("/customer/profile/{id}")
    public Object profile(@PathVariable Long id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Profile");
        return "customer/customer-profile";
    }

    @PostMapping("customer/profile/{id}/updateProfilePicture")
    public Object updateProfilePicture(@PathVariable Long id, @RequestParam(value = "profilePicturePath") MultipartFile file,
    RedirectAttributes model){
        Customer customer = customerService.getCustomerById(id);
        if (file.isEmpty()) {
            model.addFlashAttribute("errorPFP", "Please select a file.");
            return "redirect:/customer/profile/" + id;
        }
        customerService.updateCustomerPFP(id, customer, file);
        model.addFlashAttribute("successPFP", "Profile picture updated successfully");
        return "redirect:/customer/profile/" + id;
    }

    @PostMapping("/customer/profile/{id}/updateUsername")
    public Object updateUsername(@PathVariable Long id, @RequestParam(value = "username") String username,
    @RequestParam(value = "email") String email,
    @RequestParam(value = "password") String password, RedirectAttributes model){
        Customer customer = customerService.getCustomerById(id);
        if(customerService.isUsernameTaken(username) && !customer.getUsername().equals(username)){
            model.addFlashAttribute("error", "Username is already taken");
            return "redirect:/customer/profile/" + id;
        }
        if(!customer.getEmail().equals(email)){
            model.addFlashAttribute("error", "Email does not match");
            return "redirect:/customer/profile/" + id;
        }
        if(!customer.getPassword().equals(password)){
            model.addFlashAttribute("error", "Incorrect password");
            return "redirect:/customer/profile/" + id;
        }
        customer.setUsername(username);
        customerService.updateCustomer(id, customer);
        model.addFlashAttribute("success", "Username updated successfully");
        return "redirect:/customer/profile/" + id;
    }

    @PostMapping("/customer/profile/{id}/updatePassword")
    public Object updatePassword(@PathVariable Long id, 
    @RequestParam(value = "current-password") String currentPassword,
    @RequestParam(value = "password") String newPassword,
    @RequestParam(value = "re-password") String rePassword, RedirectAttributes model){
        Customer customer = customerService.getCustomerById(id);
        if(!customer.getPassword().equals(currentPassword)){
            model.addFlashAttribute("errorPassword", "Current password is incorrect");
            return "redirect:/customer/profile/" + id;
        }
        if(!newPassword.equals(rePassword)){
            model.addFlashAttribute("errorPassword", "New passwords do not match");
            return "redirect:/customer/profile/" + id;
        }
        customer.setPassword(newPassword);
        customerService.updateCustomer(id, customer);
        model.addFlashAttribute("successPassword", "Password updated successfully");
        return "redirect:/customer/profile/" + id + "?success=passwordUpdated";
    }



    @GetMapping("/customer/home/{id}")
    public Object home(@PathVariable Long id, Model model){
        model.addAttribute("groupList", groupService.getAllGroups());
        model.addAttribute("groupLocations", groupService.getDistinctGroupLocations());
        model.addAttribute("groupTypes", groupService.getDistinctGroupTypes());
        model.addAttribute("title", "GroupLink");
        return "customer/customer-home";
    }

    @GetMapping("/customer/home/{id}/search")
    public Object searchGroups(@PathVariable Long id, @RequestParam(value = "query", required = false) String query, Model model){
        model.addAttribute("groupLocations", groupService.getDistinctGroupLocations());
        model.addAttribute("groupTypes", groupService.getDistinctGroupTypes());
        model.addAttribute("groupList", groupService.searchGroupsByName(query));
        model.addAttribute("title", "GroupLink");
        return "customer/customer-home";
    }

    @GetMapping("/customer/home/{id}/filter")
    public Object Groups(@PathVariable Long id, @RequestParam(value = "type", required = false) String type,
    @RequestParam(value = "location", required = false) String location, 
    @RequestParam(value = "rating", required = false) String rating ,Model model){
        model.addAttribute("groupLocations", groupService.getDistinctGroupLocations());
        model.addAttribute("groupTypes", groupService.getDistinctGroupTypes());
        model.addAttribute("groupList", groupService.filterGroups(type, location, rating));
        model.addAttribute("title", "GroupLink");
        return "customer/customer-home";
    }

    @GetMapping("/group/details/{id}/{customerId}")
    public Object getGroupDetails(@PathVariable Long id, @PathVariable Long customerId, Model model){
        long providerId = groupService.getGroupById(id).getProvider().getId();
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("title", "Group Details");
        model.addAttribute("memberList", groupService.getGroupMembers(id));
        model.addAttribute("reviewList", providerService.getAllReviewsForProvider(providerId));
        return "customer/group-details";
    }

}
