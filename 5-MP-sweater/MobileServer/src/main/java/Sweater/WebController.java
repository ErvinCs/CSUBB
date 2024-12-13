package Sweater;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://10.0.2.2:8080")
@RestController
public class WebController {

    private PostRepository postRepository;

    public WebController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public Iterable<Post> getAllPosts() {return postRepository.findAll();}

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Optional<Post> getPostById(@PathVariable Long id)
    {
        return postRepository.findById(id);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public Post insertOne(@RequestBody Post post)
    {
        Post newPost = new Post();

        newPost.setUserName(post.getUserName());
        newPost.setActivityName(post.getActivityName());
        newPost.setMemberLimit(post.getMemberLimit());
        newPost.setDate(post.getDate());
        newPost.setTime(post.getTime());
        newPost.setLocation(post.getLocation());
        newPost.setDescription(post.getDescription());

        postRepository.save(newPost);
        return newPost;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public String deleteOne(@PathVariable Long id)
    {
        if(postRepository.findById(id).isPresent())
            postRepository.deleteById(id);
        return "";
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public Post updateOne(@RequestBody Post post, @PathVariable Long id)
    {
        Post p = postRepository.findById(id).orElse(null);
        if(post.getUserName() != null)
            p.setUserName(post.getUserName());
        if(post.getActivityName() != null)
            p.setActivityName(post.getActivityName());
        if(post.getMemberLimit() != null)
            p.setMemberLimit(post.getMemberLimit());
        if(post.getDate() != null)
            p.setDate(post.getDate());
        if(post.getTime() != null)
            p.setTime(post.getTime());
        if(post.getLocation() != null)
            p.setLocation(post.getLocation());
        if(post.getDescription() != null)
            p.setDescription(post.getDescription());

        postRepository.save(p);

        return post;
    }
}
