package uz.gigalab.sarafan.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gigalab.sarafan.domain.Comment;
import uz.gigalab.sarafan.domain.User;
import uz.gigalab.sarafan.domain.Views;
import uz.gigalab.sarafan.service.CommentService;
import uz.gigalab.sarafan.web.rest.errors.BadRequestAlertException;
import uz.gigalab.sarafan.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private static final String ENTITY_NAME = "Comment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @PostMapping
    @JsonView(Views.Full.class)
    public ResponseEntity<Comment> create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user
    ) throws URISyntaxException {

        if (comment.getId() != null) {
            throw new BadRequestAlertException("A new Comment cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Comment result = commentService.create(comment, user);

        return ResponseEntity.created(new URI("/comments/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}




















