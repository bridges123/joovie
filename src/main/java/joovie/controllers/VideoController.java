package joovie.controllers;

import joovie.models.Video;
import joovie.repos.VideoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class VideoController {
    private final VideoRepository videoRepository;
//    private final VideoService videoService;

    public VideoController(VideoRepository videoRepository/*, VideoService videoService*/) {
        this.videoRepository = videoRepository;
//        this.videoService = videoService;
    }

    @GetMapping
    public String main(Model model) {
        model.addAttribute("videos", videoRepository.findAll());
        return "video/main";
    }

    @GetMapping("/add-video")
    public String addVideo() {
        videoRepository.save(new Video(
                "vhdk56yjkgf",
                "Maximka",
                "",
                "fgjdsfkgl",
                "dfsgsdfgdsfg",
                new Date(),
                35,
                "",
                new ArrayList<>()
        ));
        return "redirect:/";
    }

    @GetMapping("/video")
    public String showVideo(@RequestParam("v") long video_id, Model model) {
        Video video = videoRepository.findById(video_id).orElse(null);
        if (video == null)
            return "redirect:/";

        model.addAttribute("video", video);
        model.addAttribute("comments", video.getComments());

        return "video/video";
    }

    @GetMapping("/trends")
    public String trends(Model model) {
        return "video/trends";
    }

    @GetMapping("/followers")
    @PreAuthorize("hasAuthority('developers:read')")
    public String followers(Model model) {
        return "video/followers";
    }

    @GetMapping("/history")
    @PreAuthorize("hasAuthority('developers:read')")
    public String history(Model model) {
        return "video/history";
    }
}
