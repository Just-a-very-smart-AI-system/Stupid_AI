package com.Stupid_AI.Stupid_AI.Controller;

import com.Stupid_AI.Stupid_AI.Entity.MessageEntity;
import com.Stupid_AI.Stupid_AI.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // Thêm tin nhắn với câu hỏi và trả lời
    @PostMapping("/add/{conversationId}")
    public String addMessage(
            @PathVariable Long conversationId,
            @RequestBody String quest) {
        MessageEntity createdMessage = messageService.addMessage(conversationId, quest);

        return createdMessage.getAns();
    }

    // Lấy tất cả tin nhắn
    @GetMapping("/all")
    public ResponseEntity<List<MessageEntity>> getAllMessages() {
        List<MessageEntity> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/conver/{id}")
    public Iterable<MessageEntity> FindByConver(@PathVariable Long id){
        return messageService.findByCover(id);
    }
    // Lấy tin nhắn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MessageEntity>> getMessageById(@PathVariable Long id) {
        Optional<MessageEntity> message = messageService.getMessageById(id);
        if (message.isPresent()) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa tin nhắn
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
