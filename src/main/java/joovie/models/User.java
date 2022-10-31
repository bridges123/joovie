package joovie.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String UID;

    @Email(message = "Invalid email")
    private String email;

    private String password;

    private String username;

    private String avatar;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public User(String UID, String email, String password, String username, String avatar, Role role, Status status) {
        this.UID = UID;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
    }
}
