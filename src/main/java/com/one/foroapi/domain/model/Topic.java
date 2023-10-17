package com.one.foroapi.domain.model;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import com.one.foroapi.util.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
@Entity(name = "Topic")
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Topic(CreateTopicDTO createTopicDTO) {
        this.title = createTopicDTO.title();
        this.description = createTopicDTO.description();
        this.status = Status.OPEN;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.deleted_at = null;

        if (createTopicDTO.userId() != null) {
            User user = new User();
            user.setId(createTopicDTO.userId());
            this.user = user;
        }

        if (createTopicDTO.categoryId() != null) {
            Category category = new Category();
            category.setId(createTopicDTO.categoryId());
            this.category = category;
        }
    }

    public void deleteLogical() {
        this.status = Status.DELETED;
        this.deleted_at = LocalDateTime.now();
    }
}
