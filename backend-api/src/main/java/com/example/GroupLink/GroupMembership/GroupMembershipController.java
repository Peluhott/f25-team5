package com.example.GroupLink.GroupMembership;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Customer.CustomerService;
import com.example.GroupLink.CustomerNotification.CustomerNotificationService;
import com.example.GroupLink.Group.Group;
import com.example.GroupLink.Group.GroupRepository;
import com.example.GroupLink.Group.GroupService;

@Controller
public class GroupMembershipController {

    private GroupMembershipService groupMembershipService;
    private CustomerService customerService;
    private GroupService groupService;
    private CustomerNotificationService customerNotificationService;
    private GroupRepository groupRepository;

    public GroupMembershipController(GroupMembershipService groupMembershipService, CustomerService customerService,
            GroupService groupService, CustomerNotificationService customerNotificationService,
            GroupRepository groupRepository) {
        this.groupMembershipService = groupMembershipService;
        this.customerService = customerService;
        this.groupService = groupService;
        this.customerNotificationService = customerNotificationService;
        this.groupRepository = groupRepository;
    }
    // create controller to update group membership status to approved or denied

    @PostMapping("/application/updateStatus")
    public Object updateStatus(@RequestParam Long membershipId, @RequestParam String status,
            @RequestParam Long providerId) {
        groupMembershipService.updateGroupMembershipStatus(membershipId, status);
        GroupMembership mem = groupMembershipService.getGroupMembershipById(membershipId);
        Group group = mem.getGroup();
        Customer customer = mem.getCustomer();
        Long customerId = customer.getId();
        String type = "";
        String message = "";
        if (status.equalsIgnoreCase("active")) {
            type = "accepted";
            message = "You've been accepted into " + mem.getGroup().getName();
            group.setActiveMem(group.getActiveMem() + 1);
            groupRepository.save(group);

        } else {
            type = "rejected";
            message = "Your application was denied for " + mem.getGroup().getName();
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
        Group group = mem.getGroup();
        group.setActiveMem(group.getActiveMem() - 1);
        groupRepository.save(group);

        customerNotificationService.createCustomerNotification(customerId, type, message);
        return "redirect:/provider/home/" + providerId;
    }

}
