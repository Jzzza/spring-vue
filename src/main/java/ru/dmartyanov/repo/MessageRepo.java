package ru.dmartyanov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmartyanov.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
