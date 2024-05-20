package com.meriem.casavia.repositories;
import java.util.Set;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.meriem.casavia.entities.Message;
import com.meriem.casavia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findBySenderAndRecipientOrRecipientAndSender(User sender, User recipient, User sender2, User recipient2);
	@Query("SELECT DISTINCT m.recipient.id FROM Message m WHERE m.sender.id = :userId")
    Set<Long> findDistinctRecipientIdsBySenderId(Long userId);

    @Query("SELECT DISTINCT m.sender.id FROM Message m WHERE m.recipient.id = :userId")
    Set<Long> findDistinctSenderIdsByRecipientId(Long userId);

}
