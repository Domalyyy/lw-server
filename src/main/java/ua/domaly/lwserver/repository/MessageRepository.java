package ua.domaly.lwserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.domaly.lwserver.entity.Message;
import ua.domaly.lwserver.entity.User;

import java.util.List;

/**
 * Repository layer for {@link Message}.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m " +
            "FROM Message m " +
            "WHERE m.sender = :sender AND m.recipient = :recipient " +
            "   OR m.sender = :recipient AND m.recipient = :sender " +
            "ORDER BY m.posted ASC")
    List<Message> findByRecipientOrSenderOrderByPostedAsc(@Param("sender") User sender, @Param("recipient") User recipient);

    @Query("SELECT m " +
            "FROM Message m " +
            "WHERE m.id IN (" +
            "   SELECT MAX(l.id) " +
            "   FROM Message l " +
            "   WHERE l.sender = :user OR l.recipient = :user " +
            "   GROUP BY " +
            "       CASE " +
            "           WHEN l.recipient = :user THEN l.sender " +
            "           WHEN l.sender = :user THEN l.recipient " +
            "           ELSE :user " +
            "       END) " +
            "ORDER BY m.posted DESC")
    List<Message> findLastMessagesByUser(@Param("user") User user);
}
