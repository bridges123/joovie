package joovie.controllers.rest;

import joovie.models.user.User;
import joovie.models.video.Dislike;
import joovie.models.video.Like;
import joovie.models.video.Video;
import joovie.models.video.View;
import joovie.repos.user.CommentRepository;
import joovie.repos.user.UserRepository;
import joovie.repos.video.DislikeRepository;
import joovie.repos.video.LikeRepository;
import joovie.repos.video.VideoRepository;
import joovie.repos.video.ViewRepository;
import joovie.services.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/video")
public class VideoRestController {
    private final VideoRepository videoRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final UserRepository userRepository;
    private final ViewRepository viewRepository;

    private final VideoService videoService;


    public VideoRestController(VideoRepository videoRepository, LikeRepository likeRepository, DislikeRepository dislikeRepository, UserRepository userRepository, ViewRepository viewRepository, VideoService videoService) {
        this.videoRepository = videoRepository;
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
        this.userRepository = userRepository;
        this.viewRepository = viewRepository;

        this.videoService = videoService;
    }

    @PostMapping("/update-view")
    public ResponseEntity<String> createView(@RequestBody View view) {
        User user = userRepository.findById(view.getUser().getId()).orElse(null);
        Video video = videoRepository.findById(view.getVideo().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        if (video == null) {
            return ResponseEntity.badRequest().body("Video not found!");
        }

        view.setViewDate(new Date());
        viewRepository.save(view);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update-view")
    public ResponseEntity<String> updateView(@RequestBody View gotView) {
        View view = viewRepository.findByUserIdAndVideoId(
                gotView.getUser().getId(),
                gotView.getVideo().getId()
        ).orElse(null);

        if (view == null) {
            return ResponseEntity.badRequest().body("User or video not found!");
        }
        view.setViewTime(gotView.getViewTime());
        viewRepository.save(view);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-like")
    public ResponseEntity<String> createLike(@RequestBody Like like) {
        User user = userRepository.findById(like.getUser().getId()).orElse(null);
        Video video = videoRepository.findById(like.getVideo().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        if (video == null) {
            return ResponseEntity.badRequest().body("Video not found!");
        }

        videoService.saveLike(like);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/update-like")
    public ResponseEntity<String> deleteLike(@RequestBody Like gotLike) {
        Like like = likeRepository.findByUserIdAndVideoId(
                gotLike.getUser().getId(),
                gotLike.getVideo().getId()
        ).orElse(null);

        if (like == null) {
            return ResponseEntity.badRequest().body("User or video not found!");
        }

        likeRepository.delete(like);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-dislike")
    public ResponseEntity<String> createDislike(@RequestBody Dislike dislike) {
        User user = userRepository.findById(dislike.getUser().getId()).orElse(null);
        Video video = videoRepository.findById(dislike.getVideo().getId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found!");
        }
        if (video == null) {
            return ResponseEntity.badRequest().body("Video not found!");
        }

        videoService.saveDislike(dislike);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/update-dislike")
    public ResponseEntity<String> deleteDislike(@RequestBody Dislike gotDislike) {
        Dislike dislike = dislikeRepository.findByUserIdAndVideoId(
                gotDislike.getUser().getId(),
                gotDislike.getVideo().getId()
        ).orElse(null);

        if (dislike == null) {
            return ResponseEntity.badRequest().body("User or video not found!");
        }

        dislikeRepository.delete(dislike);
        return ResponseEntity.ok().build();
    }
}
