package com.Stupid_AI.Stupid_AI.Service;
import com.Stupid_AI.Stupid_AI.Entity.ConversationEntity;
import com.Stupid_AI.Stupid_AI.Entity.MessageEntity;
import com.Stupid_AI.Stupid_AI.Repository.ConversationRepository;
import com.Stupid_AI.Stupid_AI.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    // Thêm mới một tin nhắn
    public MessageEntity addMessage(Long conversationId, MessageEntity message) {
        Optional<ConversationEntity> conversation = conversationRepository.findById(conversationId);
        if (conversation.isPresent()) {
            message.setConversation(conversation.get());
            return messageRepository.save(message);
        } else {
            throw new RuntimeException("Conversation not found");
        }
    }

    // Lấy tất cả tin nhắn
    public List<MessageEntity> getAllMessages() {
        return messageRepository.findAll();
    }

    // Lấy tin nhắn theo ID
    public Optional<MessageEntity> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    // Xóa tin nhắn
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
