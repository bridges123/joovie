package joovie.controllers.user;

import joovie.models.validators.UserValidator;
import joovie.repos.user.UserRepository;
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
        bindingResult = userValidator.validateUser(bindingResult, user);
        if (bindingResult.hasErrors())
            return "user/registration";

        userService.saveUser(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model) {
        joovie.models.user.User currentUser = userRepository.findByEmail(user.getUsername()).orElse(null);
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

        joovie.models.user.User currentUser = userRepository.findByEmail(user.getUsername()).orElse(null);
        if (currentUser == null)
            return "redirect:/logout";

        model.addAttribute("channels", currentUser.getFollowing());
        return "user/following";
    }
}
