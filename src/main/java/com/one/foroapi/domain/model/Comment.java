package com.one.foroapi.domain.model;

import com.one.foroapi.domain.dto.comment.CreateCommentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Entity(name = "Comment")
@EqualsAndHashCode(of = "id")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime created_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public Comment(CreateCommentDTO createCommentDTO) {
        this.content = createCommentDTO.content();
        this.created_at = LocalDateTime.now();

        if (createCommentDTO.userId() != null) {
            User user = new User();
            user.setId(createCommentDTO.userId());
            this.user = user;
        }

        if (createCommentDTO.postId() != null) {
            Post post = new Post();
            post.setId(createCommentDTO.postId());
            this.post = post;
        }
    }
}
