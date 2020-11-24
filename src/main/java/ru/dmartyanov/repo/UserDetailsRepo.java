package ru.dmartyanov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmartyanov.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
