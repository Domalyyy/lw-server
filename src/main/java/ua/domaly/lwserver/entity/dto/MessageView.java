package ua.domaly.lwserver.entity.dto;


import lombok.Getter;
import lombok.Setter;
import ua.domaly.lwserver.entity.Message;
import ua.domaly.lwserver.entity.User;

import java.util.Date;

@Setter
@Getter
public class MessageView {
    private Integer senderId;
    private String senderName;
    private Integer recipientId;
    private String recipientName;
    private String body;
    private Integer interlocutor;
    private String avatar;
    private Date posted;

    public MessageView(Message message) {
        final User sender = message.getSender();
        final User recipient = message.getRecipient();

        this.senderId = sender.getId();
        this.senderName = sender.getFirstName();
        this.recipientId = recipient.getId();
        this.recipientName = recipient.getFirstName();
        this.body = message.getBody().replace("\n", "\\n");
        this.posted = message.getPosted();
    }
}
