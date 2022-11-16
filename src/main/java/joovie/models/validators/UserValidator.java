package joovie.models.validators;

import joovie.models.user.User;
import joovie.repos.user.UserRepository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public boolean checkEmailValidity(String email) {
//        return Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z]+$")
//                .matcher(email)
//                .matches();
//    }

//    public boolean checkUsernameValidity(String username) {
//        return !username.isEmpty() && username.length() < 50;
//    }

    public boolean checkPasswordValidity(String password) {
        return password.length() >= 6 && password.length() <= 32;
    }

    public BindingResult validateUser(BindingResult bindingResult, User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "email.exists", "Пользователь с таким E-Mail уже существует!");
        }
        if (!checkPasswordValidity(user.getPassword())) {
            bindingResult.rejectValue("password", "password.error", "Пароль должен быть длиной 6-32 символа!");
        }
        return bindingResult;
    }
}
