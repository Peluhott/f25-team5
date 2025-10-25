package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group_memberships")
public class GroupMembershipController {

    private GroupMembershipService groupMembershipService;

    public GroupMembershipController(GroupMembershipService groupMembershipService) {
        this.groupMembershipService = groupMembershipService;
    }

    @PostMapping
    public GroupMembership createGroupMembership(GroupMembership groupMembership) {
        return groupMembershipService.createGroupMembership(groupMembership);
    }

    @GetMapping("/customer/{id}")
    public List<GroupMembership> getGroupMembershipsByCustomerId(Long id) {
        return groupMembershipService.getGroupMembershipsByCustomerId(id);
    }

    @GetMapping("/group/{id}")
    public List<GroupMembership> getGroupMembershipsByGroupId(Long id) {
        return groupMembershipService.getGroupMembershipsByGroupId(id);
    }

    @DeleteMapping("/{id}")
    public void cancelGroupMembership(Long id) {
        groupMembershipService.cancelGroupMembership(id);
    }

    
}
