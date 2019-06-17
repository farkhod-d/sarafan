package uz.gigalab.sarafan.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import uz.gigalab.sarafan.domain.Message;
import uz.gigalab.sarafan.domain.Views;
import uz.gigalab.sarafan.service.MessageService;
import uz.gigalab.sarafan.web.rest.errors.BadRequestAlertException;
import uz.gigalab.sarafan.web.util.HeaderUtil;
import uz.gigalab.sarafan.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
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

    /**
     * {@code POST} : Create a new productCategories.
     *
     * @param message the message to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productCategoriesDTO, or with status {@code 400 (Bad Request)} if the productCategories has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<Message> createProductCategories(@RequestBody Message message) throws URISyntaxException {
        log.debug("REST request to save Message : {}", message);
        if (message.getId() != null) {
            throw new BadRequestAlertException("A new productCategories cannot already have an ID", ENTITY_NAME, "idexists");
        }
        message.setCreatedOn(LocalDateTime.now());
        Message result = messageService.save(message);
        return ResponseEntity.created(new URI("/messages/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping
    public ResponseEntity<Message> updateProductCategories(@RequestBody Message message) throws URISyntaxException {
        log.debug("REST request to update Messages : {}", message);
        if (message.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Message result = messageService.save(message);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        log.debug("REST request to delete Messages : {}", id);
        messageService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    @JsonView(Views.Name.class)
    public Message change(Message message) throws InterruptedException {
        //Thread.sleep(1000); // simulated delay
        return messageService.save(message);
    }

}




































