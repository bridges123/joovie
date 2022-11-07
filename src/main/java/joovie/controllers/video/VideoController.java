package joovie.controllers.video;

import joovie.models.video.Video;
import joovie.repos.user.UserRepository;
import joovie.repos.video.VideoRepository;
import joovie.services.UIDGenerator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class VideoController {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public VideoController(VideoRepository videoRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String main(Model model) {
        List<Video> videos = videoRepository.findAll();
        model.addAttribute(
                "videos",
                videos.subList(0, Math.min(videos.size(), 20))
        );
        return "video/main";
    }

    @GetMapping("/add-video")
    public String addVideo() {
        videoRepository.save(new Video(
                UIDGenerator.generateUID(),
                "Maximka",
                "",
                "video_path",
                "preview_path",
                new Date(),
                12390,
                "tags",
                new ArrayList<>()
        ));
        return "redirect:/";
    }

    @GetMapping("/video")
    public String showVideo(@AuthenticationPrincipal User authUser,
                            @RequestParam("v") String uid, Model model) {
        Video video = videoRepository.findByUid(uid).orElse(null);
        if (video == null)
            return "redirect:/";

        joovie.models.user.User user = null;
        boolean followed = false;
        if (authUser != null) {
            user = userRepository.findByEmail(authUser.getUsername()).orElse(null);
            if (user == null) {
                return "redirect:/logout";
            }
            followed = userRepository.userIsFollowedOn(user.getId(), video.getUser().getId());
        }
        model.addAttribute("user", user);
        model.addAttribute("followed", followed);
        model.addAttribute("video", video);
        model.addAttribute("comments", video.getComments());

        return "video/video";
    }

    @GetMapping("/trends")
    public String trends(Model model) {
        return "video/trends";
    }

    @GetMapping("/history")
    @PreAuthorize("hasAuthority('developers:read')")
    public String history(Model model) {
        return "video/history";
    }
}
