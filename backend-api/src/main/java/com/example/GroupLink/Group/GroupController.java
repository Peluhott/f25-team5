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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.GroupLink.GroupMembership.GroupMembership;

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
            groupService.save(group);
        }

        model.addAttribute("groups", groups);
        model.addAttribute("providerId", id);

        return "/provider/provider-groups";
    }

    @GetMapping("/group/create/{providerId}")
    public Object getCreateGroup(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId);
        return "provider/create-group";
    }

    @PostMapping("/group/create")
    public Object createGroup(@ModelAttribute Group group,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam(value = "providerId") Long providerId) {
        if (providerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "providerId is required");
        }
        groupService.createGroup(group, providerId, picture);
        return "redirect:/provider/group/" + providerId;
    }

    @GetMapping("/provider/{providerId}/group/{groupId}")
    public String providerGroupDetail(@PathVariable Long providerId,
            @PathVariable Long groupId,
            Model model) {
        Group group = groupService.getGroupById(groupId);

        List<GroupMembership> memberList = groupService.getGroupMembers(groupId);
        group.setActiveMem(memberList != null ? memberList.size() : 0);

        model.addAttribute("group", group);
        model.addAttribute("memberList", memberList);
        model.addAttribute("providerId", providerId);

        return "provider/group-detail";
    }

    @GetMapping("/provider/{providerId}/group/{groupId}/edit")
    public String editGroupForm(@PathVariable Long providerId,
            @PathVariable Long groupId,
            Model model) {
        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            return "redirect:/provider/group/" + providerId;
        }
        model.addAttribute("group", group);
        model.addAttribute("providerId", providerId);
        return "provider/update-group-detail";
    }

    @PostMapping("/provider/{providerId}/group/{groupId}/update")
    public String updateGroup(@PathVariable Long providerId,
            @PathVariable Long groupId,
            @ModelAttribute Group group,
            @RequestParam(value = "picture", required = false) MultipartFile picture,
            RedirectAttributes redirectAttributes) {
        try {
            groupService.updateGroup(groupId, group, picture);
            return "redirect:/provider/" + providerId + "/group/" + groupId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/provider/" + providerId + "/group/" + groupId + "/edit";
        }
    }

    // get group
    // add group, pass provider id in param
    // update group, pass group id in param
    // delete group, a post function

}
