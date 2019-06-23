package uz.gigalab.sarafan.service;

import uz.gigalab.sarafan.domain.Comment;
import uz.gigalab.sarafan.domain.User;

public interface CommentService {

    Comment create(Comment comment, User user);
}
