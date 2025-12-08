package com.example.GroupLink.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.GroupLink.Group.GroupService;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.GroupMembership.GroupMembershipService;
import com.example.GroupLink.Provider.ProviderService;
import com.example.GroupLink.Review.Review;
import com.example.GroupLink.Review.ReviewService;

@Controller
public class CustomerController {
    
    private CustomerService customerService;
    private GroupService groupService;
    private ProviderService providerService;
    private GroupMembershipService groupMembershipService;
    private ReviewService reviewService;

    public CustomerController(CustomerService customerService, GroupService groupService, ProviderService providerService, GroupMembershipService groupMembershipService, ReviewService reviewService) {
        this.customerService = customerService;
        this.groupService = groupService;
        this.providerService = providerService;
        this.groupMembershipService = groupMembershipService;
        this.reviewService = reviewService;
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
        model.addAttribute("success", "Account registered successfully");
        customerService.createCustomer(customer, profilePicturePath);
        return "signup/customer-signup";
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
        model.addAttribute("groupList", groupService.getAllActiveGroups());
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

    @PostMapping("/group/details/{id}/{customerId}/apply")
    public Object applyToGroup(@PathVariable Long id, @PathVariable Long customerId, @RequestParam(value = "message") String message , RedirectAttributes model){
        if(groupMembershipService.doesGroupMembershipExist(customerId, id)){
            model.addFlashAttribute("error", "You have already applied or are a member of this group");
            return "redirect:/group/details/" + id + "/" + customerId;
        }
        GroupMembership membership = new GroupMembership();
        membership.setGroupId(id);
        membership.setCustomerId(customerId);
        membership.setStatus("pending");
        membership.setMessage(message);
        groupMembershipService.createGroupMembership(membership);
        model.addFlashAttribute("success", "Application submitted successfully");
        return "redirect:/group/details/" + id + "/" + customerId;
    }

    @GetMapping("/customer/notifications/{id}")
    public Object getCustomerNotifications(@PathVariable Long id, Model model){
        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("notificationsList", customerService.getCustomerNotifications(id));
        model.addAttribute("title", "Notifications");
        return "customer/customer-notification";
    }

    @GetMapping("/customer/groups/{id}")
    public Object getCustomerGroups(@PathVariable Long id, Model model){
        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("activeGroupMembershipsList", customerService.getCustomerActiveGroupMemberships(id));
        model.addAttribute("inactiveGroupMembershipsList", customerService.getCustomerInactiveGroupMemberships(id));
        model.addAttribute("pendingGroupMembershipsList", customerService.getCustomerPendingGroupMemberships(id));
        model.addAttribute("title", "My Groups");
        return "customer/customer-groups";
    }

    @GetMapping("/customer/group/details/{id}/{customerId}")
    public Object getCustomerGroupDetails(@PathVariable Long id, @PathVariable Long customerId, Model model){
        long providerId = groupService.getGroupById(id).getProvider().getId();
        model.addAttribute("group", groupService.getGroupById(id));
        model.addAttribute("title", "Group Details");
        model.addAttribute("isMember", groupMembershipService.isCustomerMemberOfGroup(customerId, id));
        model.addAttribute("isPending", groupMembershipService.isCustomerPendingInGroup(customerId, id));
        model.addAttribute("isInactive", groupService.isGroupInactive(id));
        model.addAttribute("isRemoved", groupMembershipService.isMembershipRemoved(customerId, id));
        model.addAttribute("memberList", groupService.getGroupMembers(id));
        model.addAttribute("reviewList", providerService.getAllReviewsForProvider(providerId));
        return "customer/customer-group-details";
    }

    @PostMapping("/customer/group/leave/{groupId}/{customerId}")
    public Object leaveGroup(@PathVariable Long groupId, @PathVariable Long customerId, RedirectAttributes model){
        Long membershipId = groupMembershipService.getGroupMembershipIdByCustomerAndGroup(customerId, groupId);
        groupMembershipService.removeGroupMembership(membershipId);
        model.addFlashAttribute("success", "You have left the group successfully");
        return "redirect:/customer/groups/" + customerId;
    }

    @GetMapping("/customer/group/cancelApplication/{groupId}/{customerId}")
    public Object cancelGroupApplication(@PathVariable Long groupId, @PathVariable Long customerId, RedirectAttributes model){
        Long membershipId = groupMembershipService.getGroupMembershipIdByCustomerAndGroup(customerId, groupId);
        groupMembershipService.cancelGroupMembership(membershipId);
        model.addFlashAttribute("success", "You have cancelled your application successfully");
        return "redirect:/customer/groups/" + customerId;
    }

    @PostMapping("/customer/review/{groupId}/{customerId}")
    public Object submitReview(@PathVariable Long groupId, @PathVariable Long customerId, @RequestParam(value= "rating") int rating, @RequestParam(value = "message") String message ,RedirectAttributes model){
        long providerId = groupService.getGroupById(groupId).getProvider().getId();
        model.addFlashAttribute("success", "Review submitted successfully");
        Review review = new Review();
        review.setCustomer(customerService.getCustomerById(customerId));
        review.setProvider(providerService.getProviderById(providerId));
        review.setMessage(message);
        review.setRating(rating);
        reviewService.createReview(providerId, review);
        return "redirect:/customer/group/details/" + groupId + "/" + customerId;
    }

}
