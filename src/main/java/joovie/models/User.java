package joovie.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


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

    @Size(max = 1024)
    private String description;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Video> videos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="followers_rel",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="follower_id")})
    private Set<User> followers;

    @ManyToMany(mappedBy = "followers")
    private Set<User> following;

    public User(String UID, String email, String password, String username, String avatar, Role role, Status status) {
        this.UID = UID;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
    }

    public String getAbsoluteUrl(HttpServletRequest request) {
        return "%s://%s:%d/channel/%s".formatted(
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                this.UID
        );
    }
}
