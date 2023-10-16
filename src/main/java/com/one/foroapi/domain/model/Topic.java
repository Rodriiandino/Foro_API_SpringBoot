package com.one.foroapi.domain.model;

import com.one.foroapi.domain.dto.topic.CreateTopicDTO;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Topic(CreateTopicDTO createTopicDTO) {
        this.title = createTopicDTO.title();
        this.description = createTopicDTO.description();

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

}
