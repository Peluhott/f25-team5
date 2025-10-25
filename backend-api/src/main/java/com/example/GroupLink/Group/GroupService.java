package com.example.GroupLink.Group;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.GroupLink.Provider.ProviderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class GroupService {

    GroupRepository groupRepository;
    MembershipRepository membershipRepository; // change this name if it doesn't match yours
    CustomerRepository customerRepositry; // change this name if it doesn't match yours

    @Autowired // beans for each one will be injected here
    public GroupService(GroupRepository groupRepository, MembershipRepository membershipRepository,
            CustomerRepository customerRepository) {
        this.customerRepositry = customerRepository;
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
    }

    @Transactional
    public Membership addMembership(Long groupId, Long customerId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // checks to see if there's already a membership for this customer and gruop
        if (membershipRepository.existsByGroupIdAndUserId(groupId, customerId)) {
            throw new IllegalStateException("customer already in group");
        }

        /*
         * if you have constructor that takes in user and group
         * you can pass it in the new membership(user, group)
         * other wise you can uncomment the setters on membership
         */
        Membership m = new Membership();

        // m.setUser(user);
        // m.setGroup(group);

        // just incase you don't have a default value of "pending" when creating
        // membership
        // m.setStatus("pending")

        group.addMembership(m); // adds the membership to the list in group
        groupRepository.save(group); // updates the group object

        return m; // returns the membership you can delete this if you don't want it returned
    }

    @Transactional
    public void removeMembership(Long groupId, Long membershipId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));

        Membership m = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new EntityNotFoundException("Membership not found"));

        // checks to make sure its the right group just incase
        if (!m.getGroup().getGroupID().equals(groupId)) {
            throw new IllegalArgumentException("Membership doesn't belong to this group");
        }

        group.removeMembership(m); // removes the membership in list which also triggers orphan removal
        groupRepository.save(group); // updates any changes
    }

    public Optional<Group> getGroupById(@PathVariable long id) {
        return Optional.ofNullable(groupRepository.findById(id).orElse(null));
    }

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public Group updateGroup(Long groupId, Group group) {
        Group groupToUpdate = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException(groupId + " not found"));

        groupToUpdate.setCreatorId(group.getCreatorId());
        groupToUpdate.setName(group.getName());
        groupToUpdate.setType(group.getType());
        groupToUpdate.setLocation(group.getLocation());
        groupToUpdate.setActive(group.getActive());
        groupToUpdate.setMaxMem(group.getMaxMem());
        groupToUpdate.setContent(group.getContent());
        return groupToUpdate;
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

}
