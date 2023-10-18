package com.one.foroapi.domain.service;

import com.one.foroapi.domain.dto.post.CreatePostDTO;
import com.one.foroapi.domain.dto.post.UpdatePostDTO;
import com.one.foroapi.domain.model.Post;
import com.one.foroapi.domain.model.Topic;
import com.one.foroapi.domain.model.User;
import com.one.foroapi.domain.repository.PostRepository;
import com.one.foroapi.domain.repository.TopicRepository;
import com.one.foroapi.domain.repository.UserRepository;
import com.one.foroapi.util.TimeLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public PostService(
            PostRepository postRepository ,
            UserRepository userRepository ,
            TopicRepository topicRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }


    public Post createPost(CreatePostDTO createPostDTO) {
        Post post = new Post(createPostDTO);

        if (createPostDTO.userId() != null) {
            User user = userRepository.findById(createPostDTO.userId()).orElse(null);
            post.setUser(user);
        }

        if (createPostDTO.topicId() != null) {
            Topic topic = topicRepository.findById(createPostDTO.topicId()).orElse(null);
            post.setTopic(topic);
        }

        return postRepository.save(post);
    }

    public Page<Post> getAllPosts(Pageable pagination) {
        return postRepository.findAll(pagination);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updatePost(Long postId, UpdatePostDTO updatePost) {
        Post post = getPostById(postId);

        if (!TimeLimit.isEditableWithinTimeLimit(post.getCreated_at())) {
            throw new RuntimeException("The topic is not editable after 15 minutes of its creation dat time");
        }

        if (updatePost.title() != null) {
            post.setTitle(updatePost.title());
        }

        if (updatePost.content() != null) {
            post.setContent(updatePost.content());
        }

        return postRepository.save(post);
    }
}
