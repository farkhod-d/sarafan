package uz.gigalab.sarafan.service;

import uz.gigalab.sarafan.domain.Message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageService {

    Message save(Message message);

    Page<Message> findAll(Pageable pageable);

    Optional<Message> findOne(Long id);

    void delete(Long id);
}
