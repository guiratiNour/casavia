package com.meriem.casavia.services;
import java.util.List;
import com.meriem.casavia.entities.User;
import com.meriem.casavia.entities.Message;
public interface MessageService {
    Message sendMessage(Message message);

    List<Message> getConversation(User sender, User recipient);
    
}