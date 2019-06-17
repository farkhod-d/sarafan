package uz.gigalab.sarafan.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import uz.gigalab.sarafan.domain.Message;
import uz.gigalab.sarafan.domain.Views;
import uz.gigalab.sarafan.dto.EventType;
import uz.gigalab.sarafan.service.MessageService;
import uz.gigalab.sarafan.web.rest.errors.BadRequestAlertException;
import uz.gigalab.sarafan.web.util.HeaderUtil;
import uz.gigalab.sarafan.web.util.ResponseUtil;
import uz.gigalab.sarafan.web.util.WsSender;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/messages")
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final BiConsumer<EventType, Message> wsSender;

    private static final String ENTITY_NAME = "Message";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @GetMapping
    @JsonView(Views.Name.class)
    public ResponseEntity<List<Message>> getAllMessages(
            Pageable pageable,
            @RequestParam MultiValueMap<String, String> queryParams,
            UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Message");
        Page<Message> page = messageService.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("{id}")
    @JsonView(Views.Full.class)
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        log.debug("REST request to get Messages : {}", id);
        Optional<Message> result = messageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) throws URISyntaxException {
        log.debug("REST request to save Message : {}", message);
        if (message.getId() != null) {
            throw new BadRequestAlertException("A new productCategories cannot already have an ID", ENTITY_NAME, "idexists");
        }
        message.setCreatedOn(LocalDateTime.now());
        Message result = messageService.save(message);

        wsSender.accept(EventType.CREATE, result);

        return ResponseEntity.created(new URI("/messages/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<Message> updateMessage(@RequestBody Message message) throws URISyntaxException {
        log.debug("REST request to update Messages : {}", message);
        if (message.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Message result = messageService.save(message);

        wsSender.accept(EventType.UPDATE, result);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Message message) {
        log.debug("REST request to delete Messages : {}", message.getId());
        messageService.delete(message);

        wsSender.accept(EventType.REMOVE, message);

        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, message.getId().toString()))
                .build();
    }

//    @MessageMapping("/changeMessage")
//    @SendTo("/topic/activity")
//    @JsonView(Views.Name.class)
//    public Message change(Message message) throws InterruptedException {
//        return messageService.save(message);
//    }

}




































