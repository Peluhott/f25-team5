package com.example.GroupLink.GroupMembership;

import java.util.ArrayList;
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

    public GroupMembershipService(GroupMembershipRepository groupMembershipRepository, CustomerService customerService,
            GroupService groupService) {
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

    public boolean doesGroupMembershipExist(Long customerId, Long groupId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByCustomer(
                customerService.getCustomerById(customerId));
        for (GroupMembership membership : memberships) {
            if (membership.getGroup().getId().equals(groupId)) {
                return true;
            }
        }
        return false;
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

    public void updateGroupMembershipStatus(Long id, String status) {
        GroupMembership membershipToUpdate = getGroupMembershipById(id);
        membershipToUpdate.setStatus(status);

        groupMembershipRepository.save(membershipToUpdate);

    }

    public List<GroupMembership> getPendingApplicationsByProvider(Long id) {
        List<GroupMembership> memberships = groupMembershipRepository.findByGroup_Provider_Id(id);
        List<GroupMembership> pendingMem = new ArrayList<>();

        if (memberships != null) {
            for (GroupMembership m : memberships) {
                String status = m.getStatus();
                if (status != null && "pending".equalsIgnoreCase(status.trim())) {
                    pendingMem.add(m);
                }
            }
        }

        return pendingMem;
    }

    public long getGroupMembershipIdByCustomerAndGroup(Long customerId, Long groupId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByCustomer(
                customerService.getCustomerById(customerId));
        for (GroupMembership membership : memberships) {
            if (membership.getGroup().getId().equals(groupId)) {
                return membership.getId();
            }
        }
        throw new IllegalArgumentException("Group membership not found for the given customer and group IDs");
    }

    public boolean isCustomerMemberOfGroup(Long customerId, Long groupId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByCustomer(
                customerService.getCustomerById(customerId));
        for (GroupMembership membership : memberships) {
            if (membership.getGroup().getId().equals(groupId)
                    && "accepted".equalsIgnoreCase(membership.getStatus().trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean isCustomerPendingInGroup(Long customerId, Long groupId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByCustomer(
                customerService.getCustomerById(customerId));
        for (GroupMembership membership : memberships) {
            if (membership.getGroup().getId().equals(groupId)
                    && "pending".equalsIgnoreCase(membership.getStatus().trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean isMembershipRemoved(Long customerId, Long groupId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByCustomer(
                customerService.getCustomerById(customerId));
        for (GroupMembership membership : memberships) {
            if (membership.getGroup().getId().equals(groupId)
                    && "removed".equalsIgnoreCase(membership.getStatus().trim())) {
                return true;
            }
        }
        return false;
    }

    public void cancelGroupMembership(Long id) {
        GroupMembership groupMembership = groupMembershipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group membership not found"));
        groupMembershipRepository.delete(groupMembership);
    }

    public GroupMembership removeGroupMembership(Long id) {
        GroupMembership groupMembership = groupMembershipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group membership not found"));
        groupMembership.setStatus("removed");
        return groupMembershipRepository.save(groupMembership);
    }
}
