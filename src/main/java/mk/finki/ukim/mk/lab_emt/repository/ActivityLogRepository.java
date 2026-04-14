package mk.finki.ukim.mk.lab_emt.repository;

import mk.finki.ukim.mk.lab_emt.model.domain.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}