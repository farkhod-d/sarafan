package uz.gigalab.sarafan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gigalab.sarafan.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
