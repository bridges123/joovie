package joovie.repos.video;

import joovie.models.video.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ViewRepository extends JpaRepository<View, Long> {
    Optional<View> findByUserIdAndVideoId(long userId, long videoId);
}
