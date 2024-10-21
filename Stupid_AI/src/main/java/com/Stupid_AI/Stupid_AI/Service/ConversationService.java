package com.Stupid_AI.Stupid_AI.Service;

import com.Stupid_AI.Stupid_AI.Entity.ConversationEntity;
import com.Stupid_AI.Stupid_AI.Entity.UserEntity;
import com.Stupid_AI.Stupid_AI.Repository.ConversationRepository;
import com.Stupid_AI.Stupid_AI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserService userService;

    // Thêm mới một cuộc trò chuyện
    public ConversationEntity addConversation(Integer userId, String name) {
        UserEntity user = userService.getUserById(userId);
        ConversationEntity conversation = new ConversationEntity();
        conversation.setName(name);
        conversation.setTimeChat(LocalDateTime.now());
        if (user != null) {
            conversation.setUser(user);
            return conversationRepository.save(conversation);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Lấy tất cả các cuộc trò chuyện
    public List<ConversationEntity> getAllConversations() {
        return conversationRepository.findAll();
    }

    // Lấy cuộc trò chuyện theo ID
    public ConversationEntity getConversationById(Long id) {
        return conversationRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong co Id"));
    }

    // Xóa cuộc trò chuyện
    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }
}
