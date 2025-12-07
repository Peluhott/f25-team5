package com.example.GroupLink.Provider;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Collections;

import com.example.GroupLink.Group.Group;
import com.example.GroupLink.Group.GroupService;
import com.example.GroupLink.GroupMembership.GroupMembership;
import com.example.GroupLink.GroupMembership.GroupMembershipService;
import com.example.GroupLink.Review.ReviewService;
import com.example.GroupLink.Review.Review;

@Controller
public class ProviderController {

    private ProviderService providerService;
    private GroupMembershipService groupMembershipService;
    private ReviewService reviewService;
    private GroupService groupService;

    public ProviderController(ProviderService providerService,
            GroupMembershipService groupMembershipService,
            ReviewService reviewService,
            GroupService groupService) {
        this.providerService = providerService;
        this.groupMembershipService = groupMembershipService;
        this.reviewService = reviewService;
        this.groupService = groupService;
    }

    @GetMapping("/provider/{id}/applications")
    public Object returnApplications(@PathVariable Long id, Model model) {
        List<GroupMembership> applications = groupMembershipService.getPendingApplicationsByProvider(id);
        model.addAttribute("people", applications);
        model.addAttribute("providerId", id);
        return ("provider/view-applications");
    }

    @GetMapping("/provider/login")
    public Object login() {
        return "provider/provider-login";
    }

    @GetMapping("/provider/signup-options")
    public Object signupOptions() {
        return "signup/signup-option";
    }

    @GetMapping("/provider/signup")
    public Object signup(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/provider-signup";
    }

    @PostMapping("/provider/login")
    public Object loginPost(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password, Model model) {
        if (providerService.authenticate(username, password)) {
            return "redirect:/provider/home/" + providerService.getProviderByUsername(username).getId();
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login/login-screen";
        }
    }

    @PostMapping("/provider/signup")
    public Object signupPost(@RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "re-password") String rePassword,
            @RequestParam(value = "profilePicturePath", required = false) MultipartFile profilePicturePath,
            Model model) {
        // basic validations
        if (providerService.getProviderByUsername(username) != null) {
            model.addAttribute("error", "Username is already taken");
            model.addAttribute("provider", new Provider());
            return "signup/provider-signup";
        }

        if (!password.equals(rePassword)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("provider", new Provider());
            return "signup/provider-signup";
        }

        Provider provider = new Provider();
        provider.setUsername(username);
        provider.setEmail(email);
        provider.setPassword(password);

        try {
            Provider saved = providerService.addProvider(provider, profilePicturePath);
            return "redirect:/provider/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("provider", provider);
            return "signup/provider-signup";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            model.addAttribute("provider", provider);
            return "signup/provider-signup";
        }
    }

    @GetMapping("/provider/profile/{providerId}")
    public String providerProfile(@PathVariable Long providerId, Model model) {
        Provider provider = providerService.getProviderById(providerId);

        model.addAttribute("provider", provider);
        model.addAttribute("id", providerId);
        return "provider/provider-profile";
    }

    @PostMapping("/provider/profile/{id}/updateProfilePicture")
    public String updateProfilePicture(@PathVariable Long id,
            @RequestParam("profilePicturePath") MultipartFile profilePicturePath,
            Model model) {
        try {
            Provider updated = providerService.updateProfilePicture(id, profilePicturePath);
            model.addAttribute("successPFP", "Profile picture updated");
            model.addAttribute("provider", updated);
        } catch (Exception e) {
            model.addAttribute("errorPFP", e.getMessage());
            model.addAttribute("provider", providerService.getProviderById(id));
        }
        model.addAttribute("id", id);
        return "redirect:/provider/profile/" + id;
    }

    @PostMapping("/provider/profile/{id}/updateUsername")
    public String updateUsername(@PathVariable Long id,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String currentPassword,
            Model model) {
        try {
            Provider updated = providerService.updateProfile(id, username, email, currentPassword);
            model.addAttribute("success", "Profile updated");
            model.addAttribute("provider", updated);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("provider", providerService.getProviderById(id));
        }
        model.addAttribute("id", id);
        return "provider/provider-profile";
    }

    @PostMapping("/provider/profile/{id}/updatePassword")
    public String updatePassword(@PathVariable Long id,
            @RequestParam("current-password") String currentPassword,
            @RequestParam("password") String newPassword,
            @RequestParam("re-password") String rePassword,
            Model model) {
        try {
            Provider updated = providerService.updatePassword(id, currentPassword, newPassword, rePassword);
            model.addAttribute("successPassword", "Password updated");
            model.addAttribute("provider", updated);
        } catch (Exception e) {
            model.addAttribute("errorPassword", e.getMessage());
            model.addAttribute("provider", providerService.getProviderById(id));
        }
        model.addAttribute("id", id);
        return "provider/provider-profile";
    }

    @GetMapping("/provider/home/{id}")
    public String providerHome(@PathVariable Long id, Model model) {
        Provider provider = providerService.getProviderById(id);
        List<Group> groups = provider != null && provider.getGroups() != null ? provider.getGroups()
                : Collections.emptyList();

        for (Group group : groups) {
            int activeMem = groupService.getGroupMembers(group.getId()).size();
            group.setActiveMem(activeMem);
            groupService.save(group);
        }
        model.addAttribute("groups", groups);
        model.addAttribute("providerId", id);
        model.addAttribute("provider", provider);
        return "provider/provider-groups";
    }

    @GetMapping("/provider/{providerId}/reviews")
    public String viewReviews(@PathVariable Long providerId, Model model) {
        List<Review> reviews = reviewService.getReviewsForProvider(providerId);
        model.addAttribute("reviews", reviews);
        model.addAttribute("providerId", providerId);
        return "provider/provider-reviews";
    }

}
