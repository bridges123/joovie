package joovie.repos.user;

import joovie.models.video.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select count(*) > 0 from comment_likes where user_id = :user_id and comment_id = :comment_id",
            nativeQuery = true)
    boolean isLikedByUser(@Param("user_id") long userId, @Param("comment_id") long commentId);
}
