package joovie.controllers;

import joovie.repos.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model) {
        joovie.models.User currentUser = userRepository.findByEmail(user.getUsername()).orElse(null);
        if (currentUser == null) {
            return "redirect:/logout";
        }
        model.addAttribute("user", currentUser);
        model.addAttribute("videos", currentUser.getVideos());
        return "user/profile";
    }

    @GetMapping("/following")
    public String followsPage(@AuthenticationPrincipal User user, Model model) {
        if (user == null)
            return "redirect:/logout";

        joovie.models.User currentUser = userRepository.findByEmail(user.getUsername()).orElse(null);
        if (currentUser == null)
            return "redirect:/logout";

        model.addAttribute("channels", currentUser.getFollowing());
        return "user/following";
    }
}
