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

@Transactional
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public List<Task> findByProgrammingLanguageAndUserId(final String programmingLanguage, final Integer userId) {
        return taskRepository.findFirst10TaskDistinctByProgrammingLanguageAndUsersIdNot(programmingLanguage, userId);
    }

    @Override
    public Integer complete(final TaskAnswer taskAnswer) {
        final var user = userService.findById(taskAnswer.getUserId())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        final var answers = taskAnswer.getUserAnswers()
                .stream()
                .map(answer -> answer.split(" "))
                .collect(Collectors.toList());

        final List<Task> tasks = new ArrayList<>();
        answers.forEach(answer -> {
            if (answer[1].equals("correct")) {
                tasks.add(
                        taskRepository.findById(Integer.valueOf(answer[0]))
                                .orElseThrow(() -> new IllegalStateException("Task not found"))
                );
            }
        });

//        tasks.forEach(task -> user.getCompletedTasks().forEach(completedTasks -> {
//                    if (!task.getId().equals(completedTasks.getId())) {
//                        user.addCompletedTask(completedTasks);
//                        task.addPassedUser(user);
//                    }
//                })
//        );

        tasks.forEach(user::addCompletedTask);

//        user.setCompletedTasks(tasks);
        userService.update(user);

//        final List<User> users = new ArrayList<>();
//        users.add(user);
//
//        tasks.forEach(task -> users.addAll(task.getUsers()));
//        users.forEach(passedUser -> tasks.forEach(task -> {
//            if (passedUser.task.getUsers())
//        }));

//        tasks.forEach(task -> task.getUsers().forEach(passedUser -> {
//            if (!passedUser.getId().equals(user.getId())) {
//                task.addPassedUser(user);
//            }
//        }));

//        tasks.forEach(task -> {
//            users.addAll(task.getUsers());
//        });
//        tasks.forEach(task -> users.add(task.getUsers()));
//        tasks.forEach(task -> task.setUsers(users));
        tasks.forEach(task -> {
            task.addPassedUser(user);
        });
        taskRepository.saveAll(tasks);

        return tasks.size();
    }
}
