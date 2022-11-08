package joovie.repos.user;

import joovie.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
//    long countAllByFollowers(User user);

    @Query(value = "select count(f) > 0 from follows f where f.follower_id = :follower_id and f.user_id = :user_id", nativeQuery = true)
    boolean userIsFollowedOn(@Param("follower_id") long followerId, @Param("user_id") long userId);

    @Query(value = "select count(l) > 0 from likes l where l.user_id = :user_id and l.video_id = :video_id", nativeQuery = true)
    boolean videoIsLikedByUser(@Param("user_id") long userId, @Param("video_id") long videoId);
}
