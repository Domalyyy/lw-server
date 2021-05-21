package ua.domaly.lwserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.Task;
import ua.domaly.lwserver.entity.dto.TaskAnswer;
import ua.domaly.lwserver.repository.TaskRepository;
import ua.domaly.lwserver.service.TaskService;
import ua.domaly.lwserver.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Transactional
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> findByProgrammingLanguageAndUserId(final String programmingLanguage, final Integer userId) {
        final var tasksByProgrammingLanguage = taskRepository.findTasksByProgrammingLanguage(programmingLanguage);
        final var tasksByUsersIdNot = taskRepository.findTasksByUsersId(userId);

        return tasksByProgrammingLanguage.stream()
                .filter(element -> !tasksByUsersIdNot.contains(element))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer complete(final TaskAnswer taskAnswer) {
        var user = userService.findById(taskAnswer.getUserId())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        final var answers = taskAnswer.getUserAnswers()
                .stream()
                .map(answer -> answer.split(" "))
                .collect(Collectors.toList());

        final List<Task> answeredTasks = new ArrayList<>();
        answers.forEach(answer -> {
            if (answer[1].equals("correct")) {
                answeredTasks.add(
                        taskRepository.findById(Integer.valueOf(answer[0]))
                                .orElseThrow(() -> new IllegalStateException("Task not found"))
                );
            }
        });

        answeredTasks.forEach(user::addCompletedTask);
        user = userService.checkAndUpdateGradation(user);
        userService.update(user);

        final var finalUser = user;
        answeredTasks.forEach(task -> task.addPassedUser(finalUser));
        taskRepository.saveAll(answeredTasks);

        return answeredTasks.size();
    }
}
