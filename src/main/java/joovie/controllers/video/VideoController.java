package joovie.controllers.video;

import joovie.models.video.Video;
import joovie.repos.user.UserRepository;
import joovie.repos.video.VideoRepository;
import joovie.services.UIDGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.Date;


@Controller
@RequestMapping("/video")
public class VideoController {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public VideoController(VideoRepository videoRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
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

    @GetMapping("/get-info")
    @ResponseBody
    public ResponseEntity getInfo(@AuthenticationPrincipal User authUser,
                          @RequestParam("v") String uid) {
        if (authUser != null) {
            joovie.models.user.User user = userRepository.findByEmail(authUser.getUsername()).orElse(null);
            if (user != null) {
                JsonObjectBuilder builder = Json.createObjectBuilder();
                Video video = videoRepository.findByUid(uid).orElse(new Video());
                builder.add("user_id", user.getId());
                builder.add("video_id", video.getId());
                return ResponseEntity.ok(builder.build());
            }
        }
        return ResponseEntity.badRequest().body(null);
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
        if (authUser != null) {
            joovie.models.user.User user = userRepository.findByEmail(authUser.getUsername()).orElse(null);
            if (user == null) {
                return "redirect:/logout";
            }
            userId = user.getId();
            followed = userRepository.userIsFollowedOn(user.getId(), video.getUser().getId());
            liked = userRepository.videoIsLikedByUser(user.getId(), video.getId());
        }

//        model.addAttribute("user", user);
        model.addAttribute("user_id", userId);
        model.addAttribute("followed", followed);
        model.addAttribute("video", video);
        model.addAttribute("liked", liked);
        model.addAttribute("comments", video.getComments());

        return "video/video";
    }

//    @GetMapping("/trends")
//    public String trends(Model model) {
//        return "video/trends";
//    }
//
//    @GetMapping("/history")
//    @PreAuthorize("hasAuthority('developers:read')")
//    public String history(Model model) {
//        return "video/history";
//    }
}
