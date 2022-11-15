package joovie.controllers.video;

import joovie.models.video.Video;
import joovie.repos.user.FollowRepository;
import joovie.repos.user.UserRepository;
import joovie.repos.video.DislikeRepository;
import joovie.repos.video.LikeRepository;
import joovie.repos.video.VideoRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/video")
public class VideoController {
    private final VideoRepository videoRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public VideoController(VideoRepository videoRepository, LikeRepository likeRepository, DislikeRepository dislikeRepository, UserRepository userRepository, FollowRepository followRepository) {
        this.videoRepository = videoRepository;
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @GetMapping("/add") // POST
    public String addVideo() {
//        videoRepository.save(new Video(
//                UIDGenerator.generateUID(),
//                "Maximka",
//                "",
//                "video_path",
//                "preview_path",
//                new Date(),
//                12390,
//                // User
//                new ArrayList<>(),
//                // Likes
//        ));
        return "redirect:/";
    }

    @GetMapping("")
    public String showVideo(@AuthenticationPrincipal User authUser,
                            @RequestParam("v") String uid, Model model) {
        Video video = videoRepository.findByUid(uid).orElse(null);
        if (video == null)
            return "redirect:/"; // add 404 page

        long userId = 0;
        boolean followed = false;
        boolean liked = false;
        boolean disliked = false;
        if (authUser != null) {
            joovie.models.user.User user = userRepository.findByEmail(authUser.getUsername()).orElse(null);
            if (user == null) {
                return "redirect:/logout";
            }
            userId = user.getId();
            followed = followRepository.findByUserIdAndFollowerId(video.getUser().getId(), user.getId()).isPresent();
            liked = likeRepository.findByUserIdAndVideoId(user.getId(), video.getId()).isPresent();
            disliked = dislikeRepository.findByUserIdAndVideoId(user.getId(), video.getId()).isPresent();
        }

        model.addAttribute("user_id", userId);
        model.addAttribute("followed", followed);
        model.addAttribute("video", video);
        model.addAttribute("liked", liked);
        model.addAttribute("disliked", disliked);
        model.addAttribute("comments", video.getComments());

        return "video/video";
    }
}
