package joovie.repos.video;

import joovie.models.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(long id);
    Optional<Video> findByUid(String uid);
    List<Video> findByTitleContains(String name);
}
