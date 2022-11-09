package joovie.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import joovie.models.video.Comment;
import joovie.models.video.Like;
import joovie.models.video.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user", schema = "public")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String UID;

    @Email(message = "Некорректный E-Mail")
    private String email;

    private String password;

    @NotNull(message = "Введите имя пользователя")
    private String username;

    private String avatar;

    @Max(value = 1024, message = "Слишком длинное описание (макс. 1024 символа)")
    private String description;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Video> videos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Like> likes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "follows",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    private Set<User> followers;

    @ManyToMany(mappedBy = "followers")
    private Set<User> following;

    public User(String UID, String email, String password, String username, String avatar, String description, Role role, Status status, List<Video> videos, List<Comment> comments, List<Like> likes, Set<User> followers, Set<User> following) {
        this.UID = UID;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
        this.description = description;
        this.role = role;
        this.status = status;
        this.videos = videos;
        this.comments = comments;
        this.likes = likes;
        this.followers = followers;
        this.following = following;
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
