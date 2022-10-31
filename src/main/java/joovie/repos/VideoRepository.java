package joovie.repos;

import joovie.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(long id);
    List<Video> findByTitleContains(String name);
}
