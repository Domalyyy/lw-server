package ua.domaly.lwserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.Task;
import ua.domaly.lwserver.entity.dto.TaskAnswer;
import ua.domaly.lwserver.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> get(@RequestParam final String programmingLanguage, @RequestParam final Integer userId) {
        final var userView = taskService.findByProgrammingLanguageAndUserId(programmingLanguage, userId);

        return new ResponseEntity<>(userView, HttpStatus.OK);
    }

    @PatchMapping("/submit")
    public ResponseEntity<Integer> submit(@RequestBody @Valid final TaskAnswer taskAnswer) {
        final var completedTask = taskService.complete(taskAnswer);

        return new ResponseEntity<>(completedTask, HttpStatus.OK);
    }
}
