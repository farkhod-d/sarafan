package uz.gigalab.sarafan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gigalab.sarafan.domain.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    //@EntityGraph(attributePaths = {"comments"})
    Page<Message> findAll(Pageable pageable);

    //@EntityGraph(attributePaths = {"author", "comments"})
    //Optional<Message> findById(Long id);
}
