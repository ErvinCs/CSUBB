package Sweater;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long>
{
    @Override
    Optional<Post> findById(Long id);

    @Override
    void delete(Post post);
}
