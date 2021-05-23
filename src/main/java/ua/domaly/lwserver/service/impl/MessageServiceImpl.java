package ua.domaly.lwserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.domaly.lwserver.entity.Message;
import ua.domaly.lwserver.entity.dto.MessagePost;
import ua.domaly.lwserver.repository.MessageRepository;
import ua.domaly.lwserver.service.MessageService;
import ua.domaly.lwserver.service.UserService;

import java.util.List;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Override
    public void send(final MessagePost messagePost) {
        final var sender = userService.findById(messagePost.getSender()).get();
        final var recipient = userService.findById(messagePost.getRecipient()).get();

        final var message = Message.builder()
                .body(messagePost.getBody())
                .sender(sender)
                .recipient(recipient)
                .build();

        messageRepository.save(message);
    }

    @Override
    public List<Message> getDialog(final Integer userId, final Integer interlocutorId) {
        final var user = userService.findById(userId).get();
        final var interlocutor = userService.findById(interlocutorId).get();

        return messageRepository.findByRecipientOrSenderOrderByPostedAsc(user, interlocutor);
    }

    @Override
    public List<Message> getLastMessages(final Integer userId) {
        final var user = userService.findById(userId).get();

        return messageRepository.findLastMessagesByUser(user);
    }
}
