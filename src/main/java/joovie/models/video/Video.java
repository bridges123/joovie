package joovie.models.video;

import joovie.models.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "video")
@NoArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Empty UID")
    private String uid;

    @NotEmpty(message = "Empty Title")
    @Size(min = 2, max = 128, message = "Incorrect title length (2-128).")
    private String title;

    @Size(max = 2048, message = "Incorrect description length (max 2048).")
    private String description;

    @NotEmpty(message = "Empty Video URL")
    private String video;

    @NotEmpty(message = "Empty Preview URL")
    private String preview;

    @DateTimeFormat
    private Date uploaded;

    private int views;

    private String tags;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "video")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "likedVideos")
    private List<User> likedUsers;

    public Video(String uid, String title, String description, String video,
                 String preview, Date uploaded, int views, String tags, List<Comment> comments) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.video = video;
        this.preview = preview;
        this.uploaded = uploaded;
        this.views = views;
        this.tags = tags;
        this.comments = comments;
    }

    public String getAbsoluteUrl(HttpServletRequest request) {
        return "%s://%s:%d/video?v=%s".formatted(
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                this.uid
        );
    }
}