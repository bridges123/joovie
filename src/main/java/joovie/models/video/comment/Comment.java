package joovie.models.video.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import joovie.models.user.User;
import joovie.models.video.Video;
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
@Table(name = "comments")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childrenComments;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> commentLikes;

    @NotEmpty(message = "Empty comment")
    @Size(min = 1, max = 1024, message = "Comment length should be 1-1024 symbols")
    private String text;

    @DateTimeFormat
    private Date created;

    private boolean updated;

    public Comment(Video video, User user, Comment parentComment, List<Comment> childrenComments, List<CommentLike> commentLikes, String text, Date created, boolean updated) {
        this.video = video;
        this.user = user;
        this.parentComment = parentComment;
        this.childrenComments = childrenComments;
        this.commentLikes = commentLikes;
        this.text = text;
        this.created = created;
        this.updated = updated;
    }

    public boolean isLikedByUser(User user) {
        return commentLikes.stream().anyMatch(commentLike -> commentLike.getUser().equals(user));
    }
}