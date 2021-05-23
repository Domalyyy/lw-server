package ua.domaly.lwserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.dto.MessagePost;
import ua.domaly.lwserver.entity.dto.MessageView;
import ua.domaly.lwserver.service.MessageService;
import ua.domaly.lwserver.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Task rest controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping(value = "/dialog")
    public ResponseEntity<List<MessageView>> getDialog(@RequestParam final Integer userId,
                                                       @RequestParam final Integer interlocutorId) {
        final var dialog = messageService.getDialog(userId, interlocutorId);
        final var messageViews = dialog.stream()
                .map(MessageView::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageViews);
    }

    @GetMapping(value = "/last")
    public ResponseEntity<List<MessageView>> getLastMessages(@RequestParam final Integer userId) {
        final var lastMessages = messageService.getLastMessages(userId);
        final var messageViews = lastMessages.stream()
                .map(MessageView::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageViews);
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestBody @Valid final MessagePost messagePost) {
        messageService.send(messagePost);
    }
}
