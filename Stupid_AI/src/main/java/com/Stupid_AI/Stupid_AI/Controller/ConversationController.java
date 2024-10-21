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
    @PostMapping("/add")
    public ResponseEntity<ConversationEntity> addConversation(@RequestBody String name) {
        ConversationEntity conversation = conversationService.addConversation(1, name);
        return ResponseEntity.ok(conversation);
    }

    // Lấy tất cả các cuộc trò chuyện
    @GetMapping("/all")
    public ResponseEntity<List<ConversationEntity>> getAllConversations() {
        List<ConversationEntity> conversations = conversationService.getAllConversations();
        return ResponseEntity.ok(conversations);
    }

    // Lấy cuộc trò chuyện theo ID
    @GetMapping("findId/{id}")
    public ConversationEntity getConversationById(@PathVariable Long id) {
        ConversationEntity conversation = conversationService.getConversationById(id);
        return conversation;
    }

    // Xóa cuộc trò chuyện
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConversation(@PathVariable Long id) {
        conversationService.deleteConversation(id);
        return ResponseEntity.noContent().build();
    }
}

