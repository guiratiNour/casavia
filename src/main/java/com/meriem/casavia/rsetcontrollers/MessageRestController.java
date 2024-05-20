package com.meriem.casavia.rsetcontrollers;

import com.meriem.casavia.entities.Message;
import com.meriem.casavia.entities.User;
import com.meriem.casavia.repositories.MessageRepository;
import com.meriem.casavia.repositories.UserRepository;
import com.meriem.casavia.services.MessageService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam Long senderId, @RequestParam Long recipientId) {
        User sender = userRep.findById(senderId).orElse(null);
        User recipient = userRep.findById(recipientId).orElse(null);
        if (sender != null && recipient != null) {
            return messageService.getConversation(sender, recipient);
        }
        return new ArrayList<>(); // Return an empty list if one of the users is not found
    }

    @GetMapping("/user-chats")
    public ResponseEntity<Set<Long>> getUserChatIds(@RequestParam Long userId) {
        Set<Long> recipientIds = messageRepository.findDistinctRecipientIdsBySenderId(userId);
        Set<Long> senderIds = messageRepository.findDistinctSenderIdsByRecipientId(userId);
        recipientIds.addAll(senderIds); // Fusionner les deux sets pour obtenir des IDs uniques
        return ResponseEntity.ok(recipientIds);
    }
}
