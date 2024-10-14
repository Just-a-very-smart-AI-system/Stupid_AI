package com.Stupid_AI.Stupid_AI.Repository;

import com.Stupid_AI.Stupid_AI.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    // Có thể thêm các hàm tùy chỉnh nếu cần
}
