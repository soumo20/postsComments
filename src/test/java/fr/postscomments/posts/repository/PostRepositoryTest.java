package fr.postscomments.posts.repository;

import fr.postscomments.posts.dto.PostDto;
import fr.postscomments.posts.models.Post;
import fr.postscomments.posts.services.PostServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostServices postServices;

    private Post postSaved1;

    @BeforeEach
    public void setUp() {
        PostDto post1 = PostDto.builder()
                .title("Post 1")
                .content("This is the content of Post 1")
                .build();
        postSaved1 = postServices.addPost(post1);
    }

    @Test
    void existPostByIdTrue() {
        //When
        Boolean existPost = postRepository.existPostById(postSaved1.getId());

        //Then
        assertTrue(existPost);
    }


    @Test
    void existPostByIdfALSE() {
        //When
        Boolean existPost = postRepository.existPostById(5L);

        //Then
        assertFalse(existPost);
    }
}