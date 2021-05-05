package ua.domaly.lwserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.Task;
import ua.domaly.lwserver.entity.User;

import java.util.List;

/**
 * Repository layer for {@link User}.
 */
@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findFirst10TaskDistinctByProgrammingLanguageAndUsersIdNot(String programmingLanguage, Integer userId);
}
