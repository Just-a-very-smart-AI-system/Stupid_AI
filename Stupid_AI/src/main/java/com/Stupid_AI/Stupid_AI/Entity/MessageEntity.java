package com.Stupid_AI.Stupid_AI.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
