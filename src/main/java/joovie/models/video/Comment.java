package joovie.models.video;

import joovie.models.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Comment> childrenComments;

    @NotEmpty(message = "Empty comment")
    @Size(min = 1, max = 1024, message = "Comment length should be 1-1024 symbols")
    private String text;

    @DateTimeFormat
    private Date created;

    private boolean updated;

    public Comment(Video video, Comment parent, List<Comment> childrenComments, String text, Date created, boolean updated) {
        this.video = video;
        this.parent = parent;
        this.childrenComments = childrenComments;
        this.text = text;
        this.created = created;
        this.updated = updated;
    }
}