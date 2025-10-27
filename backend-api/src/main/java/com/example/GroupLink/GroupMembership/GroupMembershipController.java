package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupLink.Customer.CustomerService;
import com.example.GroupLink.Group.GroupService;

@RestController
@RequestMapping("/api/group_memberships")
public class GroupMembershipController {

    private GroupMembershipService groupMembershipService;
    private CustomerService customerService;
    private GroupService groupService;

    public GroupMembershipController(GroupMembershipService groupMembershipService, CustomerService customerService, GroupService groupService) {
        this.groupMembershipService = groupMembershipService;
        this.customerService = customerService;
        this.groupService = groupService;
    }

    @PostMapping
    public GroupMembership createGroupMembership(@Validated @RequestBody GroupMembership groupMembership) {
        return groupMembershipService.createGroupMembership(groupMembership);
    }

    @GetMapping("/customer/{customerId}")
    public List<GroupMembership> getGroupMembershipsByCustomerId(@PathVariable Long customerId) {
        return groupMembershipService.getGroupMembershipsByCustomer(customerService.getCustomerById(customerId));
    }

    @GetMapping("/group/{groupId}")
    public List<GroupMembership> getGroupMembershipsByGroup(@PathVariable Long groupId) {
        return groupMembershipService.getGroupMembershipsByGroup(groupService.getGroupById(groupId));
    }

    @DeleteMapping("/{id}")
    public void cancelGroupMembership(@PathVariable Long id) {
        groupMembershipService.cancelGroupMembership(id);
    }

    
}
