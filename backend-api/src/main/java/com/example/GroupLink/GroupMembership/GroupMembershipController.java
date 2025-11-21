package com.example.GroupLink.GroupMembership;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupLink.Customer.CustomerService;
import com.example.GroupLink.Group.GroupService;

@Controller
public class GroupMembershipController {

    private GroupMembershipService groupMembershipService;
    private CustomerService customerService;
    private GroupService groupService;

    public GroupMembershipController(GroupMembershipService groupMembershipService, CustomerService customerService,
            GroupService groupService) {
        this.groupMembershipService = groupMembershipService;
        this.customerService = customerService;
        this.groupService = groupService;
    }
    // create controller to update group membership status to approved or denied

    @PostMapping("/application/updateStatus")
    public Object updateStatus(@RequestParam Long membershipId, @RequestParam String status,
            @RequestParam Long providerId) {
        groupMembershipService.updateGroupMembershipStatus(membershipId, status);
        return "redirect:/provider/" + providerId + "/applications";
    }

}
