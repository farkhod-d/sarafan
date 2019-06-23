package uz.gigalab.sarafan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gigalab.sarafan.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
