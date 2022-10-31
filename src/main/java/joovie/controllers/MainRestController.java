package joovie.controllers;

import joovie.models.Comment;
import joovie.repos.CommentRepository;
import joovie.repos.VideoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    private final VideoRepository videoRepository;
    private final CommentRepository commentRepository;

    public MainRestController(VideoRepository videoRepository, CommentRepository commentRepository) {
        this.videoRepository = videoRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developers:read')")
    public Comment addComment(@ModelAttribute("comment") Comment comment) {
        System.out.println(comment);
        return commentRepository.save(comment);
    }
}
