package com.Stupid_AI.Stupid_AI.Controller;

import com.Stupid_AI.Stupid_AI.Entity.ConversationEntity;
import com.Stupid_AI.Stupid_AI.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    // Thêm cuộc trò chuyện
    @PostMapping("/add/{userId}")
    public ResponseEntity<ConversationEntity> addConversation(@PathVariable Integer userId) {
        ConversationEntity conversation = new ConversationEntity();
        conversation.setTimeChat(LocalDateTime.now()); // Gán thời gian hiện tại
        ConversationEntity createdConversation = conversationService.addConversation(userId, conversation);
        return ResponseEntity.ok(createdConversation);
    }

    // Lấy tất cả các cuộc trò chuyện
    @GetMapping("/all")
    public ResponseEntity<List<ConversationEntity>> getAllConversations() {
        List<ConversationEntity> conversations = conversationService.getAllConversations();
        return ResponseEntity.ok(conversations);
    }

    // Lấy cuộc trò chuyện theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ConversationEntity>> getConversationById(@PathVariable Long id) {
        Optional<ConversationEntity> conversation = conversationService.getConversationById(id);
        if (conversation.isPresent()) {
            return ResponseEntity.ok(conversation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa cuộc trò chuyện
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConversation(@PathVariable Long id) {
        conversationService.deleteConversation(id);
        return ResponseEntity.noContent().build();
    }
}

