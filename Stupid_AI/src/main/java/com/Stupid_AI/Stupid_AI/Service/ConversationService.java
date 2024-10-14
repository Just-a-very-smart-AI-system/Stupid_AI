package com.Stupid_AI.Stupid_AI.Service;

import com.Stupid_AI.Stupid_AI.Entity.ConversationEntity;
import com.Stupid_AI.Stupid_AI.Entity.UserEntity;
import com.Stupid_AI.Stupid_AI.Repository.ConversationRepository;
import com.Stupid_AI.Stupid_AI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    // Thêm mới một cuộc trò chuyện
    public ConversationEntity addConversation(Integer userId, ConversationEntity conversation) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            conversation.setUser(user.get());
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
    public Optional<ConversationEntity> getConversationById(Long id) {
        return conversationRepository.findById(id);
    }

    // Xóa cuộc trò chuyện
    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }
}
