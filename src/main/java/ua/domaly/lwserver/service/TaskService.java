package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.Task;
import ua.domaly.lwserver.entity.dto.TaskAnswer;

import java.util.List;

@Transactional
@Service
public interface TaskService {
    List<Task> findByProgrammingLanguageAndUserId(String programmingLanguage, Integer userId);

    Integer complete(TaskAnswer taskAnswer);
}
