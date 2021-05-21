package ua.domaly.lwserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.dto.ResponseDTO;
import ua.domaly.lwserver.service.UserService;

/**
 * User rest controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private static final String USER_NOT_FOUND = "User with id: %s not found";

    private final UserService userService;

    /**
     * REST API to get gradation to user by id.
     *
     * @param userId id of user.
     * @return gradation within {@link ResponseDTO}.
     */
    @GetMapping("/gradation")
    public ResponseEntity<ResponseDTO> getGradationByUserId(@RequestParam final Integer userId) {
        final var user = userService.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(USER_NOT_FOUND, userId)));
        final var gradation = user.getGradation().toString();
        final var responseDTO = new ResponseDTO();
        responseDTO.setBody(gradation);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * REST API to get count of completed task by uder id.
     *
     * @param userId id of user.
     * @return count of completed task.
     */
    @GetMapping("/completedTasks")
    public ResponseEntity<Integer> getCompletedTasksCountByUserId(@RequestParam final Integer userId) {
        final var user = userService.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(USER_NOT_FOUND, userId)));
        final var countOfCompletedTasks = user.getCompletedTasks().size();

        return new ResponseEntity<>(countOfCompletedTasks, HttpStatus.OK);
    }

    /**
     * REST API to get count that is needed to have the next graduation level.
     *
     * @param userId id of user.
     * @return count that is needed to have the next graduation level.
     */
    @GetMapping("/neededCount")
    public ResponseEntity<Integer> getNeededCountToUpdate(@RequestParam final Integer userId) {
        final var user = userService.findById(userId)
                .orElseThrow(() -> new IllegalStateException(String.format(USER_NOT_FOUND, userId)));

        return new ResponseEntity<>(userService.getNeededCountToUpdate(user), HttpStatus.OK);
    }
}
