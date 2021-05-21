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
@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    /**
     * Method to get tasks by programming language.
     *
     * @param programmingLanguage programming language.
     * @return list of tasks.
     */
    List<Task> findTasksByProgrammingLanguage(String programmingLanguage);

    /**
     * Method to get tasks to needed user.
     *
     * @param userId id of user.
     * @return list of tasks.
     */
    List<Task> findTasksByUsersId(Integer userId);
}
