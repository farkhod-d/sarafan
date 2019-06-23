package uz.gigalab.sarafan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.gigalab.sarafan.domain.Comment;
import uz.gigalab.sarafan.domain.User;
import uz.gigalab.sarafan.repository.CommentRepository;
import uz.gigalab.sarafan.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }
}
