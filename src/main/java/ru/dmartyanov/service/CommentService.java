package ru.dmartyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dmartyanov.domain.Comment;
import ru.dmartyanov.domain.User;
import ru.dmartyanov.repo.CommentRepo;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        commentRepo.save(comment);

        return comment;
    }
}