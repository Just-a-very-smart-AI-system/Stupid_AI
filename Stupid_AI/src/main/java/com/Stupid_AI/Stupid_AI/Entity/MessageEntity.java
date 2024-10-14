package com.Stupid_AI.Stupid_AI.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_conversation", nullable = false)
    private ConversationEntity conversation;

    @Column(name = "ans", nullable = false, columnDefinition = "TEXT")
    private String ans;

    @Column(name = "ques", nullable = false, columnDefinition = "TEXT")
    private String ques;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversationEntity getConversation() {
        return conversation;
    }

    public void setConversation(ConversationEntity conversation) {
        this.conversation = conversation;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }
}
