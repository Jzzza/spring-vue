package ru.dmartyanov.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmartyanov.domain.Comment;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"comments"})
    List<Comment> findAll();
}