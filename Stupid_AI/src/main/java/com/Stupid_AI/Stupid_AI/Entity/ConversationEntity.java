package com.Stupid_AI.Stupid_AI.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversations")
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_chat", nullable = false)
    private LocalDateTime timeChat;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeChat() {
        return timeChat;
    }

    public void setTimeChat(LocalDateTime timeChat) {
        this.timeChat = timeChat;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

