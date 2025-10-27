package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Customer.CustomerService;
import com.example.GroupLink.Group.Group;
import com.example.GroupLink.Group.GroupService;

@Service
public class GroupMembershipService {

    private GroupMembershipRepository groupMembershipRepository;
    private CustomerService customerService;
    private GroupService groupService;

    public GroupMembershipService(GroupMembershipRepository groupMembershipRepository, CustomerService customerService, GroupService groupService) {
        this.groupMembershipRepository = groupMembershipRepository;
        this.customerService = customerService;
        this.groupService = groupService;
    }

    public GroupMembership createGroupMembership(GroupMembership groupMembership) {
        if (groupMembership.getCustomerId() != null) {
            groupMembership.setCustomer(customerService.getCustomerById(groupMembership.getCustomerId()));
        }
        if (groupMembership.getGroupId() != null) {
            groupMembership.setGroup(groupService.getGroupById(groupMembership.getGroupId()));
        }
        return groupMembershipRepository.save(groupMembership);
    }

    public List<GroupMembership> getGroupMembershipsByCustomer(Customer customer) {
        return groupMembershipRepository.findByCustomer(customer);
    }

    public List<GroupMembership> getGroupMembershipsByGroup(Group group) {
        return groupMembershipRepository.findByGroup(group);
    }

    public GroupMembership getGroupMembershipById(long membershipId) {
        return groupMembershipRepository.getReferenceById(membershipId);
    }

    public void cancelGroupMembership(Long id) {
        GroupMembership groupMembership = groupMembershipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group membership not found"));
        groupMembershipRepository.delete(groupMembership);
    }
}
