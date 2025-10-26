package com.example.GroupLink.Group;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/group/{id}")
    public Optional<Group> getGroup(@PathVariable long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/group")
    public Group addGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @PutMapping("/group/{id}")
    public Optional<Group> updateGroup(@PathVariable long id, @RequestBody Group group) {
        groupService.updateGroup(id, group);
        return groupService.getGroupById(id);
    }

    @DeleteMapping("group/{id}")
    public void deleteGroup(@PathVariable long id) {
        groupService.deleteGroup(id);
    }
}
