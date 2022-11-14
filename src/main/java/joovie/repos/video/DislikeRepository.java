package joovie.repos.video;

import joovie.models.video.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {
    Optional<Dislike> findByUserIdAndVideoId(long userId, long videoId);
}
