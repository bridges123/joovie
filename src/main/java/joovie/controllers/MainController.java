package joovie.controllers;

import joovie.models.video.Video;
import joovie.repos.video.VideoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class MainController {
    private final VideoRepository videoRepository;

    public MainController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
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
}
