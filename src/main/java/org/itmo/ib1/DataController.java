package org.itmo.ib1;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public DataController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<PostResponse> list() {
        return postRepository.findAllByOrderByIdDesc().stream().map(PostResponse::from).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse create(@RequestBody PostRequest request, @AuthenticationPrincipal SecurityUser user) {
        if (request.content() == null || request.content().isBlank()
                || request.content().length() > Post.MAX_CONTENT_LENGTH
                || request.content().indexOf('\u0000') >= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "content must be 1-" + Post.MAX_CONTENT_LENGTH + " characters");
        }
        ApplicationUser author = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return PostResponse.from(postRepository.save(new Post(author, request.content())));
    }
}
