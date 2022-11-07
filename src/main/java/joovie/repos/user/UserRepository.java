package joovie.repos.user;

import joovie.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    long countAllByFollowers(User user);

    @Query(value = "select count(f) > 0 from followers_rel f where f.user_id = :user_id and f.follower_id = :follower_id", nativeQuery = true)
    boolean userIsFollowedOn(@Param("user_id") long userId, @Param("follower_id") long follower_id);
}
