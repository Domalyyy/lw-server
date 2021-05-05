//package ua.domaly.lwserver.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import ua.domaly.lwserver.entity.Task;
//import ua.domaly.lwserver.service.TaskService;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    private final TaskService taskService;
//
//    @GetMapping("/completerTasks")
//    public ResponseEntity<List<Task>> getCompletedTasks(@RequestParam final Integer userId) {
//        final var userView = taskService.findByProgrammingLanguageAndUserId(programmingLanguage, userId);
//
//        return new ResponseEntity<>(userView, HttpStatus.OK);
//    }
//}
