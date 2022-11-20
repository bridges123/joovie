package joovie.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import joovie.models.video.*;
import joovie.models.video.comment.Comment;
import joovie.models.video.comment.CommentLike;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users", schema = "public")
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

    @NotEmpty(message = "Введите имя пользователя")
    private String username;

    private String avatar;

    @Max(value = 1024, message = "Слишком длинное описание (макс. 1024 символа)")
    private String description;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user")
    private List<Video> videos;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<CommentLike> likedComments;

    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @OneToMany(mappedBy = "user")
    private Set<Dislike> dislikes;

    @OneToMany(mappedBy = "user")
    private List<View> views;

    @OneToMany(mappedBy = "user")
    private List<Follow> followers;

    @OneToMany(mappedBy = "follower")
    private List<Follow> following;

    public User(String UID, String email, String password, String username, String avatar, String description, Role role, Status status, List<Video> videos, List<Comment> comments, List<Like> likes, Set<Dislike> dislikes, List<View> views, List<Follow> followers, List<Follow> following) {
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
        this.dislikes = dislikes;
        this.views = views;
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
