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
@Table(name = "views")
@JsonIgnoreProperties
@NoArgsConstructor
@Getter
@Setter
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "view_time")
    private int viewTime;

    @DateTimeFormat
    private Date viewDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;
}
