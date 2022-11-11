package joovie.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import joovie.models.video.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "follows")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @DateTimeFormat
    private Date created;

    public Follow(User user, User follower, Date created) {
        this.user = user;
        this.follower = follower;
        this.created = created;
    }
}