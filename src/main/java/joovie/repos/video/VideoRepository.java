package joovie.repos.video;

import joovie.models.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(long id);
    Optional<Video> findByUid(String uid);
    List<Video> findByTitleContains(String name);

    @Modifying
    @Query(value = "insert into likes user_id = :user_id, video_id = :video_id", nativeQuery = true)
    void addLikeRel(@Param("user_id") long userId, @Param("video_id") long videoId);
}
