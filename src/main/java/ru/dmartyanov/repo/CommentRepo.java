package ru.dmartyanov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmartyanov.domain.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
