package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
}
