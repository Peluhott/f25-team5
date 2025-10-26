package com.example.GroupLink.Group;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Customer.CustomerRepository;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.GroupMembership.GroupMembershipRepository;
import com.example.GroupLink.Provider.ProviderRepository;
import com.example.GroupLink.Provider.ProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.GroupLink.Provider.Provider;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class GroupService {

    GroupRepository groupRepository;
    GroupMembershipRepository groupMembershipRepository;
    CustomerRepository customerRepository;
    ProviderService providerService;

    @Autowired // beans for each one will be injected here
    public GroupService(GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository,
            CustomerRepository customerRepository, ProviderService providerService) {
        this.customerRepository = customerRepository;
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.providerService = providerService;
    }

    public Group getGroupById(long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
    }

    public Group createGroup(Group group, Long providerId) {
        Provider provider = providerService.getProviderById(providerId);
        group.setProvider(provider);
        return groupRepository.save(group);
    }

    @Transactional
    public Group updateGroup(Long groupId, Group group) {
        Group groupToUpdate = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException(groupId + " not found"));

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
