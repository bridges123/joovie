package joovie.models.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import joovie.models.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @DateTimeFormat
    private Date created;

    public Like(User user, Video video, Date created) {
        this.user = user;
        this.video = video;
        this.created = created;
    }
}
