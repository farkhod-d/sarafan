package uz.gigalab.sarafan.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "message")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "text"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @Column
    @JsonView(Views.IdName.class)
    private String text;

    @Column(name = "created_on", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.Full.class)
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    @JsonView(Views.Full.class)
    private User author;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "message",
            orphanRemoval = true
    )
    @JsonView(Views.Full.class)
    private List<Comment> comments;

    @JsonView(Views.Full.class)
    private String link;
    @JsonView(Views.Full.class)
    private String linkTitle;
    @JsonView(Views.Full.class)
    private String linkDescription;
    @JsonView(Views.Full.class)
    private String linkCover;
}
