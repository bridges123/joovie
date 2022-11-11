package joovie.controllers.rest;

import joovie.models.user.Follow;
import joovie.models.user.User;
import joovie.repos.user.FollowRepository;
import joovie.repos.user.UserRepository;
import joovie.repos.video.VideoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public UserRestController(VideoRepository videoRepository, UserRepository userRepository, FollowRepository followRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @PostMapping("/update-follow")
    public ResponseEntity<String> updateFollow(@RequestBody Follow follow) {
        User user = userRepository.findById(follow.getUser().getId()).orElse(null);
        User follower = userRepository.findById(follow.getFollower().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        if (follower == null) {
            return ResponseEntity.badRequest().body("Follower not found!");
        }

        follow.setCreated(new Date());
        followRepository.save(follow);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/update-follow")
    public ResponseEntity<String> deleteFollow(@RequestBody Follow gotFollow) {
        Follow follow = followRepository.findByUserIdAndFollowerId(
                gotFollow.getUser().getId(),
                gotFollow.getFollower().getId()
        ).orElse(null);

        if (follow == null) {
            return ResponseEntity.badRequest().body("User of follower not found!");
        }

        followRepository.delete(follow);
        return ResponseEntity.ok().build();
    }
}
