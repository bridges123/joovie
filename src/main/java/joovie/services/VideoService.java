package joovie.services;

import joovie.models.user.User;
import joovie.models.video.Dislike;
import joovie.models.video.Like;
import joovie.models.video.Video;
import joovie.repos.video.DislikeRepository;
import joovie.repos.video.LikeRepository;
import joovie.repos.video.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;

    public VideoService(VideoRepository videoRepository, LikeRepository likeRepository, DislikeRepository dislikeRepository) {
        this.videoRepository = videoRepository;
        this.likeRepository = likeRepository;
        this.dislikeRepository = dislikeRepository;
    }

    public void saveLike(Like like) {
        dislikeRepository
                .findByUserIdAndVideoId(like.getUser().getId(), like.getVideo().getId())
                .ifPresent(dislikeRepository::delete);

        like.setCreated(new Date());
        likeRepository.save(like);
    }

    public void saveDislike(Dislike dislike) {
        likeRepository
                .findByUserIdAndVideoId(dislike.getUser().getId(), dislike.getVideo().getId())
                .ifPresent(likeRepository::delete);

        dislike.setCreated(new Date());
        dislikeRepository.save(dislike);
    }
}
