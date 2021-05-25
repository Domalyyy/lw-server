package ua.domaly.lwserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.dto.ResponseDTO;
import ua.domaly.lwserver.entity.dto.UserDTO;
import ua.domaly.lwserver.service.UserService;

import java.util.List;

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
    @GetMapping("/completedTasks/count")
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
        final var userWithCheckedGradation = userService.checkAndUpdateGradation(user);

        return new ResponseEntity<>(userService.getNeededCountToUpdate(userWithCheckedGradation), HttpStatus.OK);
    }

    /**
     * REST API to find user with completed tasks with needed programming language.
     *
     * @param programmingLanguage programming language.
     * @param userId              id of user.
     * @return list of users as DTO.
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> getUsersByProgrammingLanguage(@RequestParam final String programmingLanguage,
                                                                       @RequestParam final Integer userId) {
        final var users = userService.getUsersByProgrammingLanguage(programmingLanguage, userId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * REST API to send friend request.
     *
     * @param firstUserId  id of user that sent request.
     * @param secondUserId if of user that can accept / decline the request.
     */
    @PutMapping("/friendRequest/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendFriendRequest(@RequestParam final Integer firstUserId, @RequestParam final Integer secondUserId) {
        userService.sendFriendRequest(firstUserId, secondUserId);
    }

    /**
     * REST API to decline friend request.
     *
     * @param firstUserId  id of user that sent request.
     * @param secondUserId if of user that can accept / decline the request.
     */
    @PutMapping("/friendRequest/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelFriendRequest(@RequestParam final Integer firstUserId, @RequestParam final Integer secondUserId) {
        userService.cancelFriendRequest(firstUserId, secondUserId);
    }

    /**
     * REST API to get friend requests,
     *
     * @param userId if of user.
     * @return list of friend requests.
     */
    @GetMapping("/friendRequest")
    public ResponseEntity<List<UserDTO>> getFriendRequests(@RequestParam final Integer userId) {
        final var users = userService.getFriendRequests(userId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * REST API to submit friend request.
     *
     * @param firstUserId  id of user that has request.
     * @param secondUserId if of user that sent the request.
     */
    @PatchMapping("/friendRequest/submit")
    @ResponseStatus(HttpStatus.OK)
    public void submitFriendRequests(@RequestParam final Integer firstUserId,
                                     @RequestParam final Integer secondUserId) {
        userService.submitFriendRequests(firstUserId, secondUserId);
    }

    /**
     * REST API to decline friend request.
     *
     * @param firstUserId  id of user that has request.
     * @param secondUserId if of user that sent the request.
     */
    @DeleteMapping("/friendRequest/decline")
    @ResponseStatus(HttpStatus.OK)
    public void declineFriendRequests(@RequestParam final Integer firstUserId,
                                      @RequestParam final Integer secondUserId) {
        userService.declineFriendRequests(firstUserId, secondUserId);
    }

    /**
     * REST API to get freinds.
     *
     * @param userId if of user.
     * @return list of friends.
     */
    @GetMapping("/friends")
    public ResponseEntity<List<UserDTO>> getFriends(@RequestParam final Integer userId) {
        final var friends = userService.getFriends(userId);

        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    /**
     * REST API to delete friend.
     *
     * @param firstUserId  id of user that deletes user.
     * @param secondUserId if of user that will be deleted.
     */
    @DeleteMapping("/friend/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriend(@RequestParam final Integer firstUserId, @RequestParam final Integer secondUserId) {
        userService.deleteFriend(firstUserId, secondUserId);
    }
}
