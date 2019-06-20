package uz.gigalab.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
@EqualsAndHashCode(of = {"id"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @Column
    @JsonView(Views.Name.class)
    private String text;

    @Column(name = "created_on", updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.Full.class)
    private LocalDateTime createdOn;

    @JsonView(Views.Full.class)
    private String link;
    @JsonView(Views.Full.class)
    private String linkTitle;
    @JsonView(Views.Full.class)
    private String linkDescription;
    @JsonView(Views.Full.class)
    private String linkCover;
}
