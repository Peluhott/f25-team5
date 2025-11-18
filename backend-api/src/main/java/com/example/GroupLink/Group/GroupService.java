package com.example.GroupLink.Group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class GroupService {

    GroupRepository groupRepository;

    CustomerRepository customerRepository;
    ProviderService providerService;
    private static final Path UPLOAD_DIR = Paths.get("backend-api/src/main/resources/static/groups/images/");

    @Autowired // beans for each one will be injected here
    public GroupService(GroupRepository groupRepository,
            ProviderService providerService) {

        this.groupRepository = groupRepository;
        this.providerService = providerService;

    }

    public Group getGroupById(long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
    }

    public Group createGroup(Group group, Long providerId, MultipartFile profilePicture) {
        Provider provider = providerService.getProviderById(providerId);
        group.setProvider(provider);

        Group savedGroup = groupRepository.save(group);

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String fileName = saveProfilePicture(savedGroup.getId(), profilePicture);
            if (fileName != null) {
                savedGroup.setProfilePicturePath(fileName);
                savedGroup = groupRepository.save(savedGroup);
            }
        }

        return savedGroup;
    }

    private String saveProfilePicture(Long groupId, MultipartFile file) {
        try {
            String original = file.getOriginalFilename();
            if (original == null || !original.contains("."))
                return null;
            String ext = original.substring(original.lastIndexOf('.') + 1);
            String fileName = "group" + groupId + "." + ext;
            Files.createDirectories(UPLOAD_DIR);
            Path target = UPLOAD_DIR.resolve(fileName);
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Group updateGroup(Long groupId, Group form, MultipartFile profilePicture) {
        Group existing = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group " + groupId + " not found"));

        existing.setName(form.getName());
        existing.setType(form.getType());
        existing.setLocation(form.getLocation());
        existing.setActive(form.getActive());
        existing.setMaxMem(form.getMaxMem());
        existing.setContent(form.getContent());

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String fileName = saveProfilePicture(groupId, profilePicture);
            if (fileName != null) {
                existing.setProfilePicturePath(fileName);
            }
        }

        return existing;
    }

    public void updateGroupSize(Long id) {
        Group groupToUpdate = getGroupById(id);
        int newSize = groupToUpdate.getActiveGroupSize();
        groupToUpdate.setActiveGroupSize(newSize);
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public List<GroupMembership> getGroupMembers(Long groupId) {
        Group group = getGroupById(groupId);
        return group.getMemberships();
    }

    public List<Group> filterGroups(String type, String location, String rating) {
        Double providerRating = rating == null ? 0.0 : Double.parseDouble(rating);
        return groupRepository.findByTypeContainingAndLocationContainingAndProviderAverageRatingGreaterThanEqual(
                type,location, providerRating);
    }

    public List<String> getDistinctGroupTypes() {
        return groupRepository.findDistinctTypes();
    }

    public List<String> getDistinctGroupLocations() {
        return groupRepository.findDistinctLocations();
    }

    public List<Group> searchGroupsByName(String query) {
        return groupRepository.findByNameContainingIgnoreCase(query);
    }

}
