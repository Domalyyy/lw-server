package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import ua.domaly.lwserver.entity.Message;
import ua.domaly.lwserver.entity.User;
import ua.domaly.lwserver.entity.dto.MessagePost;

import java.util.List;

/**
 * Service layer for {@link User}.
 */
@Service
public interface MessageService {
    void send(MessagePost messagePost);

    List<Message> getDialog(Integer userId, Integer interlocutorId);

    List<Message> getLastMessages(Integer userId);
}
