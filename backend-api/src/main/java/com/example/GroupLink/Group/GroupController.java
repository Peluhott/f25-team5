package com.example.GroupLink.Group;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("provider/group/{id}")
    public Object getGroups(@PathVariable Long id, Model model) {
        List<Group> groups = groupService.getAllGroupsForProvider(id);
        for (Group group : groups) {
            int activeMem = groupService.getGroupMembers(group.getId()).size();
            group.setActiveMem(activeMem);
        }

        model.addAttribute("groups", groups);
        model.addAttribute("providerId", id);

        return "/provider/provider-groups";
    }

    @GetMapping("/group/create")
    public Object getCreateGroup() {
        return "provider/create-group";
    }

    @PostMapping("/group/create")
    public Object createGroup(@ModelAttribute Group group,
            @RequestParam("picture") MultipartFile picture) {
        Long providerId = 1L;
        groupService.createGroup(group, providerId, picture);
        return "redirect:/provider/group/" + providerId;
    }
    // get group
    // add group, pass provider id in param
    // update group, pass group id in param
    // delete group, a post function

}
