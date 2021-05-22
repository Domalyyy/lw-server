package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.Task;
import ua.domaly.lwserver.entity.dto.TaskAnswer;

import java.util.List;

/**
 * Service layer for {@link Task}.
 */
@Service
public interface TaskService {
    /**
     * Method to get task by user id and programming language.
     *
     * @param programmingLanguage programming language.
     * @param userId              id of user.
     * @return list of tasks.
     */
    @Transactional
    List<Task> findByProgrammingLanguageAndUserId(String programmingLanguage, Integer userId);

    /**
     * Method to complete task.
     *
     * @param taskAnswer answer from user.
     * @return count correct answers.
     */
    @Transactional
    Integer complete(TaskAnswer taskAnswer);
}
