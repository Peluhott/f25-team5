package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GroupMembershipService {

    private GroupMembershipRepository groupMembershipRepository;

    public GroupMembershipService(GroupMembershipRepository groupMembershipRepository) {
        this.groupMembershipRepository = groupMembershipRepository;
    }

    public GroupMembership createGroupMembership(GroupMembership groupMembership) {
        return groupMembershipRepository.save(groupMembership);
    }

    public List<GroupMembership> getGroupMembershipsByCustomerId(Long customerId) {
        return groupMembershipRepository.findByCustomerId(customerId);
    }

    public List<GroupMembership> getGroupMembershipsByGroupId(Long groupId) {
        return groupMembershipRepository.findByGroupId(groupId);
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
