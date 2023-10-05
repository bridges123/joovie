package joovie.models.validators;

import joovie.models.user.User;
import joovie.repos.user.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public boolean checkEmailValidity(String email) {
//        return Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z]+$")
//                .matcher(email)
//                .matches();
//    }

    public boolean checkPasswordValidity(String password) {
        return password.length() >= 6 && password.length() <= 32;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "email.exists", "Пользователь с таким E-Mail уже существует!");
        }
        if (!checkPasswordValidity(user.getPassword())) {
            errors.rejectValue("password", "password.error", "Пароль должен быть длиной 6-32 символа!");
        }
    }
}
