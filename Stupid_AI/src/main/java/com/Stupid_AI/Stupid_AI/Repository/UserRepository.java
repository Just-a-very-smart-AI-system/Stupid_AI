package com.Stupid_AI.Stupid_AI.Repository;
import com.Stupid_AI.Stupid_AI.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // Có thể thêm các hàm tùy chỉnh tìm kiếm tại đây
}
