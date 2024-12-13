package Sweater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "http://10.0.2.2:8080")
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    public Post insertOne(
            String userName,
            String activityName,
            Integer memberLimit,
            String date,    //Date - datatype
            String time,    //Time - datatype
            String location,
            String description
    ) {
        Post p = new Post();
        p.setUserName(userName);
        p.setActivityName(activityName);
        p.setMemberLimit(memberLimit);
        p.setDate(date);
        p.setTime(time);
        p.setLocation(location);
        p.setDescription(description);
        postRepository.save(p);
        return p;
    }

    public String deleteOne(Long id) {
        postRepository.deleteById(id);
        return "Post<" + id.toString() + "> Deleted";
    }

    public String updateOne(
            Long id,
            String userName,
            String activityName,
            Integer memberLimit,
            String date,    //Date - datatype
            String time,    //Time - datatype
            String location,
            String description
    ) {
        Optional<Post> post = postRepository.findById(id);
        if(!userName.equals("\n"))
            post.get().setUserName(userName);
        if(!activityName.equals("\n"))
            post.get().setActivityName(activityName);
        post.get().setMemberLimit(memberLimit);
        if(!date.equals("\n"))
            post.get().setDate(date);
        if(!time.equals("\n"))
            post.get().setTime(time);
        if(!location.equals("\n"))
            post.get().setLocation(location);
        if(!description.equals("\n"))
            post.get().setDescription(description);

        postRepository.deleteById(id);
        postRepository.save(post.get());

        return "Post<" + id.toString() + "> Updated";

    }

    public Iterable<Post> getAllPosts()
    {
        return postRepository.findAll();
    }
}
