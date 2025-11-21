package com.example.GroupLink.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.GroupMembership.GroupMembershipService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProviderController {

    private ProviderService providerService;
    private GroupMembershipService groupMembershipService;

    public ProviderController(ProviderService providerService, GroupMembershipService groupMembershipService) {
        this.providerService = providerService;
        this.groupMembershipService = groupMembershipService;
    }

    @GetMapping("/provider/{id}/applications")
    public Object returnApplications(@PathVariable Long id, Model model) {
        List<GroupMembership> applications = groupMembershipService.getPendingApplicationsByProvider(id);
        model.addAttribute("people", applications);
        model.addAttribute("providerId", id);
        return ("provider/view-applications");
    }
    // get provider
    // create provider
    // login for provider
    // update provider
    // delete provider

}
