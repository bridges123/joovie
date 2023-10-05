package joovie.controllers.user;

import joovie.models.validators.UserValidator;
import joovie.repos.user.UserRepository;
import joovie.security.SecurityUser;
import joovie.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserRepository userRepository, UserService userService, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") joovie.models.user.User user) {
        return "user/registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid joovie.models.user.User user,
                               BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "user/registration";

        userService.saveUser(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User authUser, Model model) {
        Optional<joovie.models.user.User> user = userRepository.findByEmail(authUser.getUsername());
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("videos", user.get().getVideos());
            return "user/profile";
        }
        return "redirect:/logout";

    }

    @GetMapping("/following")
    public String followsPage(@AuthenticationPrincipal User authUser, Model model) {
        if (authUser == null)
            return "redirect:/logout";

        joovie.models.user.User user = userRepository.findByEmail(authUser.getUsername()).orElse(null);
        if (user == null)
            return "redirect:/logout";

        model.addAttribute("followingChannels", user.getFollowing());
        return "user/following";
    }
}
