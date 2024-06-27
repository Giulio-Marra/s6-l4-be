package giulio_marra.s6_l3.controllers;

import giulio_marra.s6_l3.entities.Post;
import giulio_marra.s6_l3.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @GetMapping
    public Page<Post> getAllPost(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "uuid") String sortBy) {
        return postService.getPosts(page, size, sortBy);
    }


}
