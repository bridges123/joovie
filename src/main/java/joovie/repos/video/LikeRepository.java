package joovie.repos.video;

import joovie.models.video.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndVideoId(long userId, long videoId);
}
