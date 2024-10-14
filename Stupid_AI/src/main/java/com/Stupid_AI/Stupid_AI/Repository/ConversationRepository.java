package com.Stupid_AI.Stupid_AI.Repository;
import com.Stupid_AI.Stupid_AI.Entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
    // Có thể thêm các hàm tùy chỉnh nếu cần
}
