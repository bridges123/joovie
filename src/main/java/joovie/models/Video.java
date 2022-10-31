package joovie.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


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
    private String uid; // = models.CharField('Ссылка', max_length=255, editable=False)

    @NotEmpty(message = "Empty Title")
    @Size(min = 2, max = 128, message = "Incorrect title length (2-128).")
    private String title; // = models.CharField('Название', max_length=255)

    @Size(max = 2048, message = "Incorrect description length (max 2048).")
    private String description; // = models.TextField('Описание')

    @NotEmpty(message = "Empty Video URL")
    private String video; // = models.FileField('Видео-файл', upload_to=user_directory_path)

    @NotEmpty(message = "Empty Preview URL")
    private String preview; // = models.ImageField('Превью', upload_to='previews/', blank=True)

    @DateTimeFormat
    private Date uploaded; // = models.DateTimeField('Дата загрузки', auto_now_add=True)

    private int views; // = models.IntegerField('Кол-во просмотров', default=0, editable=False)

    private String tags; // = models.JSONField('Тэги')

    // USER

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "video")
    private List<Comment> comments;


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
}