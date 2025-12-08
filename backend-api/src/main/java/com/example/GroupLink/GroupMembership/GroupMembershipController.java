package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Customer.CustomerService;
import com.example.GroupLink.CustomerNotification.CustomerNotification;
import com.example.GroupLink.CustomerNotification.CustomerNotificationService;
import com.example.GroupLink.Group.GroupService;

@Controller
public class GroupMembershipController {

    private GroupMembershipService groupMembershipService;
    private CustomerService customerService;
    private GroupService groupService;
    private CustomerNotificationService customerNotificationService;

    public GroupMembershipController(GroupMembershipService groupMembershipService, CustomerService customerService,
            GroupService groupService, CustomerNotificationService customerNotificationService) {
        this.groupMembershipService = groupMembershipService;
        this.customerService = customerService;
        this.groupService = groupService;
        this.customerNotificationService = customerNotificationService;
    }
    // create controller to update group membership status to approved or denied

    @PostMapping("/application/updateStatus")
    public Object updateStatus(@RequestParam Long membershipId, @RequestParam String status,
            @RequestParam Long providerId) {
        groupMembershipService.updateGroupMembershipStatus(membershipId, status);
        GroupMembership mem = groupMembershipService.getGroupMembershipById(membershipId);
        Customer customer = mem.getCustomer();
        Long customerId = customer.getId();
        String type = "";
        String message = "";
        if (status.equalsIgnoreCase("active")) {
            type = "accepted";
            message = "you have been accepted into a group!";
        } else {
            type = "rejected";
            message = "your application was denied";
        }

        customerNotificationService.createCustomerNotification(customerId, type, message);
        return "redirect:/provider/" + providerId + "/applications";
    }

    @PostMapping("/application/remove")
    public Object removeMember(@RequestParam Long membershipId, @RequestParam String status,
            @RequestParam Long providerId) {
        groupMembershipService.updateGroupMembershipStatus(membershipId, status);
        GroupMembership mem = groupMembershipService.getGroupMembershipById(membershipId);
        Customer customer = mem.getCustomer();
        Long customerId = customer.getId();
        String type = "rejected";
        String message = "you have been removed from a group!";

        customerNotificationService.createCustomerNotification(customerId, type, message);
        return "redirect:/provider/home/" + providerId;
    }

}
