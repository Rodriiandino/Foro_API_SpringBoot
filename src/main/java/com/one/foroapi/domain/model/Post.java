package com.one.foroapi.domain.model;

import com.one.foroapi.domain.dto.post.CreatePostDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
@Entity(name = "Post")
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Post(CreatePostDTO createPostDTO) {
        this.title = createPostDTO.title();
        this.content = createPostDTO.content();

        if (createPostDTO.userId() != null) {
            User user = new User();
            user.setId(createPostDTO.userId());
            this.user = user;
        }
        if (createPostDTO.topicId() != null) {
            Topic topic = new Topic();
            topic.setId(createPostDTO.topicId());
            this.topic = topic;
        }
    }
}
